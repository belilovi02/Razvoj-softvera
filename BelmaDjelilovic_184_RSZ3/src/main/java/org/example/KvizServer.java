package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KvizServer {

    //takmicari (string: ime), (integer: bodovi - default 0)
    private static final HashMap<String, Integer> takmicari = new HashMap<>();
    private static final Set<Handler> klijenti = new HashSet<>();
    private static Pitanje[] pitanja;

    public static void ucitajPitanja() {
        try {
            String myJsonString = Files.readString(Paths.get("pitanja.json"));
            ObjectMapper om = new ObjectMapper();
            pitanja = om.readValue(myJsonString, Pitanje[].class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void izmjesajPitanja() {
        List<Pitanje> pitanjaList = new ArrayList<>(Arrays.asList(pitanja));

        pitanjaList.removeIf(p -> p.getAnswers().size() < 3);
        System.out.println("Ukupno validnih pitanja (s tacno 3 ponudjena odgovora): " + pitanjaList.size());

        Collections.shuffle(pitanjaList);

        pitanja = null;

        pitanja = pitanjaList.stream().limit(10).toList().toArray(new Pitanje[10]);

        System.out.println("Pitanja izmjesana! Ukupno pitanja za trenutni kviz: " + pitanja.length);
    }
    public static void main(String[] args) {
        System.out.println("Quiz server je pokrenut!");
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(500);

        ucitajPitanja();
        if(pitanja != null) System.out.println("Pitanja ucitana! Ukupno ucitanih pitanja: " + pitanja.length);
        izmjesajPitanja();
        /*System.out.println("Pitanja za trenutni kviz:");
        for(int i = 0; i < pitanja.length; i++) System.out.println(pitanja[i]);*/

        try {
            ServerSocket listener = new ServerSocket(54321);
            while(true) {
                pool.execute(new Handler(listener.accept()));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static class Handler implements Runnable {

        private String ime;
        private boolean odgovorio;
        private boolean zavrseno;
        private int trenutnoPitanje;
        private Socket socket;
        private Scanner in;
        private PrintWriter out;

        public void pokreniKviz() {
            for (Handler klijent : klijenti) {

                Timer timer = new Timer();

                TimerTask task = new TimerTask() {
                    int pitanje = 0;
                    @Override
                    public void run() {
                        if (pitanje == 0) klijent.out.println("[INFO]*** POCETAK KVIZA ***");
                        if (pitanje == 10) {
                            klijent.out.println("[INFO]*** KRAJ KVIZA ***");
                            klijent.out.println("[INFO]*** RACUNANJE REZULTATA ***");
                            klijent.zavrseno = true;
                            klijent.odgovorio = false;
                            takmicari.entrySet().stream()
                                    .sorted((t1, t2) -> -t1.getValue().compareTo(t2.getValue()))
                                    .forEach(t -> klijent.out.println("[INFO]" + t.getKey() + ": " + t.getValue()));
                            timer.cancel();
                        }
                        else {
                            klijent.out.println("[INFO]Pitanje " + (pitanje + 1) + ": " + pitanja[pitanje]);
                            for (int i = 0; i < pitanja[pitanje].getAnswers().size(); i++) {
                                klijent.out.println("[INFO]" + pitanja[pitanje].getAnswers().get(i) + "\n");
                            }
                            klijent.trenutnoPitanje = pitanje;
                            klijent.odgovorio = false;
                            pitanje++;
                        }
                    }
                };
                timer.schedule(task, 5000, 10000);
            }
        }
        public boolean provjeriOdgovor(int unosBr) {
            Odgovor tacanOdgovor = pitanja[this.trenutnoPitanje].getAnswers().stream().filter(Odgovor::isCorrect).findFirst().get();
            String tacanStr = tacanOdgovor.getChoice();
            int tacanBr;
            if (tacanStr.equals("A")) tacanBr = 1;
            else if (tacanStr.equals("B")) tacanBr = 2;
            else tacanBr = 3;
            return unosBr == tacanBr;
        }

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

                while(true) {
                    out.println("Unesite vase ime:");
                    ime = in.nextLine();
                    if(ime == null)
                        return;

                    synchronized(takmicari) {
                        if(!ime.isEmpty() && !takmicari.containsKey(ime)) {
                            takmicari.put(ime, 0);
                            break;
                        }
                    }
                }

                out.println("Takmicar: " + ime);

                klijenti.add(this);

                for (Handler klijent: klijenti) {
                    klijent.out.println("[INFO]" + ime + " se pridruzio/la kvizu");
                    klijent.out.println("[INFO]Trenutan broj takmicara: " + klijenti.size() + " (potrebno 3 za pocetak kviza)");
                }
                if (klijenti.size() == 3) {
                    for (Handler klijent : klijenti) {
                        klijent.out.println("[INFO]Pridruzena su 3 takmicara! Kviz uskoro pocinje...");
                    }
                    pokreniKviz();
                }

                while(true) {
                    String unos = in.nextLine();
                    int unosBr = -1;

                    out.println("[INFO]" + ime + ": " + unos);

                    if (this.zavrseno) out.println("[INFO]Kviz je zavrsen! Za izlaz unesite 0!");

                    if (this.odgovorio) out.println("[INFO]Vec ste odgovorili na ovo pitanje!");

                    try {
                        unosBr = Integer.parseInt(unos);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    if (unosBr == 0) {
                        return;
                    }
                    if (unosBr > 0 && unosBr < 4 && !odgovorio && !zavrseno) {
                        this.odgovorio = true;
                        boolean isTacan = provjeriOdgovor(unosBr);
                        if (isTacan) {
                            out.println("[INFO]TACAN ODGOVOR!");
                            takmicari.merge(ime, 1, Integer::sum);
                        }
                        else out.println("[INFO]NETACAN ODGOVOR!");
                    }
                }
            }
            catch(IOException ex) {
                Logger.getLogger(KvizServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                if(out != null)
                    klijenti.remove(this);

                if(ime != null) {
                    takmicari.remove(ime);
                    for(Handler klijent : klijenti) {
                        klijent.out.println("[INFO]" + ime + " je napustio/la kviz");
                    }
                }

                try {
                    socket.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}