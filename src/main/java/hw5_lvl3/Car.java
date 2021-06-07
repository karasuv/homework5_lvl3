package hw5_lvl3;

public class Car implements Runnable{
    private static int winnerCount = 0;
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            MainClass.BARRIER.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);  //когда сам сделал ДЗ,
                                                        // то в этой строке вылазила ошибка NullPointerException
                                                        // хотя 5 раз проверил с Вашим файлом  все идентично казалось
                                                        // так и не понял где ошибся...
                                                        // и вообще почему-то у меня ДЗ в этом lvl3 очень проблемно идут :(
            }
            synchronized (race.getMon()) {
                if (winnerCount++ == 0){
                    System.out.println("Победитель: " + this.name);
                }
                else {
                    System.out.println(this.name + " пришел к финишу и занял место " + winnerCount);
                }
            }
            MainClass.BARRIER.await();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
