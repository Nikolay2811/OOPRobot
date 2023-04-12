package Robot4;

import java.util.Scanner;

public class Main {

    public static void mainMenu() {
        System.out.println(
                "Главное меню.\n1. создание робота \n2. поворот робота \n3. вывод списка роботов\n4. движение робота\n5. выход");
        System.out.println("Введите команду:");
    }

    public static void createRobot(RobotMap map) {
        System.out.println("Введите команду для создания робота: cereate-robot");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.startsWith("create-robot")) {
                String[] split = command.split(" "); // [create-robot 3 5]

                try {

                    System.out.println("РОбот создан "
                            + map.createRobot(new Point(Integer.parseInt(split[1]), Integer.parseInt(split[2]))));
                    break;
                } catch (PositionException e) {
                    System.out.println("При создании робота возникло исключение: " + e.getMessage() + "." +
                            " Попробуйте еще раз");
                }
            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
        }
        //sc.close();

    }

    public static void main(String[] args) throws PositionException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите команду для создания карты: create-map");
        RobotMap map = null;
        while (true) {
            String command = sc.nextLine();
            if (command.startsWith("create-map")) {
                String[] split = command.split(" "); // [create-map 3 5]

                try {
                    map = new RobotMap(Integer.parseInt(split[1]), Integer.parseInt(split[2]));

                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("При создании карты возникло исключение: " + e.getMessage() + "." +
                            " Попробуйте еще раз");
                }
            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
            //sc.close();
        }

        System.out.println("ИГРАЕМ...");

        createRobot(map);

        // Scanner sc1 = new Scanner(System.in);
        while (true) {
            mainMenu();
            String command = sc.nextLine();
            switch (command) {
                case ("1"):
                    createRobot(map);
                    break;
                case ("2"):

                    while (true) {
                        System.out.println("выберите робота для поворота: ");
                        map.showRobots();
                        String robotNumber = sc.nextLine();
                        int num = Integer.parseInt(robotNumber);
                        
                        if (num > 0 && num <= map.ids.size()) {
                            System.out.println("выберите направление для движения 1) TOP 2) LEFT 3)RIGHT 4) BOTTOM: ");
                            String direction = sc.nextLine();
                            switch (direction) {

                                case ("1"):

                                    map.robots.get(map.ids.get(num)).changeDirection(RobotMap.Direction.TOP);
                                    System.out.println(map.robots.get(map.ids.get(num)));

                                    break;

                                case ("2"):
                                    map.robots.get(map.ids.get(num)).changeDirection(RobotMap.Direction.LEFT);
                                    System.out.println(map.robots.get(map.ids.get(num)));
                                    break;

                                case ("3"):
                                    map.robots.get(map.ids.get(num)).changeDirection(RobotMap.Direction.RIGHT);
                                    System.out.println(map.robots.get(map.ids.get(num)));
                                    break;

                                case ("4"):
                                    map.robots.get(map.ids.get(num)).changeDirection(RobotMap.Direction.BOTTOM);
                                    System.out.println(map.robots.get(map.ids.get(num)));
                                    break;
                            }
                        }
                        else{System.out.println("Не верный номер робота");}

                        
                        break;
                    }

                case ("3"):
                    map.showRobots();
                    break;
                case ("4"):
                    while (true) {
                        System.out.println("Выберите робота для движение:");
                        map.showRobots();
                        String robotNumber1 = sc.nextLine();
                        int num1 = Integer.parseInt(robotNumber1);
                        if (num1 <= map.ids.size() && num1 > 0) {
                            try{

                            map.robots.get(map.ids.get(num1)).move();}
                            catch(PositionException e) {
                                System.out.println("Не удалось переместить робота: "+ e.getMessage());
                             }
                            System.out.println(map.robots.get(map.ids.get(num1)));
                        } else {
                            System.out.println("Не коректный номер робота");
                        }

                        break;
                    }

                    break;
                case ("5"):
                    System.exit(0);
                    break;

            }

        }
        // sc.close();
        // RobotMap map = new RobotMap(10,10);
        // RobotMap.Robot robot1 =null;
        // RobotMap.Robot robot2 =null;
        // try{
        // robot1 =map.createRobot(new Point(,5));
        // robot2 =map.createRobot(new Point(2,5));
        // System.out.println(robot1);
        // System.out.println(robot2);
        // } catch(PositionException e) {
        // System.out.println("Во время создания рлбота случилось исключение: "+
        // e.getMessage());
        // }
        // if(robot2 != null)
        // try{
        // robot1.move();
        // } catch(PositionException e) {
        // System.out.println("Не удалось переместить робота: "+ e.getMessage());
        // }
        // robot1.move();
        // robot1.changeDirection(RobotMap.Direction.RIGHT);
        // robot.move();
        // robot.move();
        // System.out.println(robot);
    }
}
