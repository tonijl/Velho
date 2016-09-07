package velho;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.applet.*;
import java.io.File;
import java.net.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Ohjaus extends JPanel implements ActionListener, KeyListener,
        MouseListener, MouseMotionListener {

    Timer t = new Timer(5, this);
    double x = 300, y = 500, velx = 0, vely = 0;
    int p = 0;
    boolean fireUnlocked = false;
    boolean iceUnlocked = false;
    boolean lightningUnlocked = false;
    boolean windUnlocked = false;
    int level = 1;
    int pisteet = 0;
    int highscore = 0;
    int skillpoints = 1;
    int spawn = 500;
    double radians = 0;
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;
    boolean firing = false;
    boolean menu = true;
    int menuselect = 1;
    int selectedMap;
    int[] monsters;
    int[] spawnTimes;
    int vuoro;
    double life = 100;
    double maxlife = 100;
    double speed = 1;
    double maxspeed = 1;
    int hpCost = 10;
    int enemyLife = 100;
    int magic = 1;
    int audio;
    int audiofire;
    double angle = 0;
    double mx, my;
    int fire;
    int ice;
    double enemySpeed = 1;
    double bulletSpeed = 6;
    double enemyBulletSpeed = 2;
    double fireRate = 200;
    double cooldown = 0;
    double enemyFireRate = 100;
    double xero, yero;
    double pLeveys = 85;
    double pKorkeus = 85;
    int fix, fiy;
    String polku = "";
    String pelaaja = "velho1.png";
    String rajahdys = "explosion.png";
    String tahtain = "tahtain.png";
    String fireIcon = "tuli.png";
    String iceIcon = "ice.png";
    String lightIcon = "salama.png";
    String earthIcon = "kivet.png";
    String windIcon = "wind.png";
    String[] icons = {"salama.png", "tuli.png", "ice.png", "kivet.png", "wind.png"};
    int[][] levels = {{1, 1, 1, 1, 1, 0},
        {5, 1, 2, 1, 1, 2, 0},
        {5, 2, 1, 2, 1, 2, 1, 0},
        {1, 2, 1, 3, 2, 1, 3, 3, 0},
        {3, 1, 3, 2, 2, 2, 3, 1, 3, 0},
        {3, 2, 3, 2, 3, 1, 1, 3, 4, 0},
        {1, 2, 3, 4, 1, 2, 3, 4, 2, 2, 4, 5, 0},
        {4, 3, 3, 1, 1, 2, 5, 4, 4, 3, 4, 3, 5, 0}};
    int pysty = 0, vaaka = 0;
    List<Bullet> luodit = new ArrayList<Bullet>();
    List<Monster> monsterit = new ArrayList<Monster>();
    List<EnemyBullet> vihuluodit = new ArrayList<EnemyBullet>();
    List<Effects> tehosteet = new ArrayList<Effects>();
    List<Upgrade> upgradet = new ArrayList<Upgrade>();
    List<Magic> taijat = new ArrayList<Magic>();
    List<Map> mapit = new ArrayList<Map>();
    Magic taika = new Magic();

    // Pisara pisara1 = new Pisara();
    //Pallo pallo1 = new Pallo();
    public Ohjaus() {
        t.start();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.darkGray);
        System.setProperty("apple.awt.fullscreenhidecursor", "true");
        // pallo1.setVipu(true);

        polku = (getClass().getResource(".").getPath());

        //public Map(int x, int y, String nimi, String description, boolean locked, boolean cleared, int[] monsters, int[] spawntime) {
        Map metsa1 = new Map(380, 495, "Road to forest", "Enemies:Boards", false, false, false, new Color(0, 102, 0),
                new int[]{6, 6, 6, 6, 6, 6, 0}, new int[]{500, 500, 500, 500, 500, 500});
        Map metsa2 = new Map(435, 560, "Forest", "Enemies:Boards and Bees", true, false, false, new Color(0, 102, 0),
                new int[]{6, 6, 7, 7, 7, 7, 6, 7, 7, 7, 6, 18, 6, 6, 0}, new int[]{400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400});
        Map metsa3 = new Map(538, 500, "Deep into forest", "Enemies:Boards and Bees\nBoss:Mad Bird", true, false, false, new Color(0, 102, 0),
                new int[]{6, 18, 6, 7, 6, 6, 7, 7, 7, 7, 7, 7, 6, 6, 18, 6, 8, 0}, new int[]{300, 300, 300, 300, 300, 300, 500, 500, 500, 300, 400, 400, 400, 400, 400, 600, 600});

        Map saari1 = new Map(280, 518, "Road to Beach", "Enemies:Crabs", false, false, false, new Color(0, 102, 0),
                new int[]{9, 9, 9, 9, 9, 9, 0}, new int[]{500, 500, 500, 500, 500, 100});
        Map saari2 = new Map(215, 608, "Beach", "Enemies:Crabs and Seagulls", true, false, false, new Color(204, 153, 0),
                new int[]{9, 10, 10, 9, 10, 9, 9, 10, 9, 10, 0}, new int[]{400, 400, 400, 400, 400, 400, 400, 400, 400, 400});
        Map saari3 = new Map(210, 695, "Sea", "Enemies:Seagulls", true, false, false, Color.BLUE,
                new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0}, new int[]{300, 300, 300, 200, 200, 200, 100, 100, 100, 1000, 50, 50, 50, 50, 50});
        Map saari4 = new Map(120, 722, "Island of the storms", "Enemies:Crabs and Seagulls\nBoss:Thunder Cloud", true, false, false, new Color(0, 102, 0),
                new int[]{9, 9, 9, 10, 10, 9, 10, 9, 10, 9, 10, 9, 9, 9, 11, 0}, new int[]{300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300});

        Map jaa1 = new Map(230, 420, "Crossroad of King", "Enemies:Soldiers", false, false, false, new Color(0, 102, 0),
                new int[]{1, 1, 1, 1, 1, 0}, new int[]{300, 300, 300, 300, 300});
        Map jaa2 = new Map(115, 380, "Entrance to Frozen land", "Enemies:Yetis", true, false, false, new Color(204, 255, 204),
                new int[]{12, 12, 12, 12, 12, 12, 12, 12, 0}, new int[]{500, 500, 500, 500, 400, 400, 400, 400});
        Map jaa3 = new Map(60, 320, "Frozen land", "Enemies:Yetis and Yeti archers", true, false, false, Color.WHITE,
                new int[]{12, 13, 12, 13, 12, 13, 12, 13, 13, 13, 0}, new int[]{400, 400, 400, 400, 400, 400, 300, 300, 50, 50, 50});
        Map jaa4 = new Map(77, 247, "Frozen Cave", "Enemies:Yetis and Yeti archers\nBoss:Snowman", true, false, false, new Color(179, 255, 255),
                new int[]{12, 13, 13, 12, 12, 13, 13, 12, 12, 13, 13, 12, 12, 13, 12, 14, 0}, new int[]{50, 500, 50, 500, 50, 500, 300, 300, 300, 300, 300, 500, 50, 500, 50, 500});

        Map tulivuori1 = new Map(358, 335, "Road to Volcano", "Enemies:Trolls", false, false, false, new Color(0, 102, 0),
                new int[]{15, 15, 15, 15, 15, 15, 15, 0}, new int[]{300, 300, 300, 300, 300, 300, 300});
        Map tulivuori2 = new Map(432, 285, "Troll Village", "Enemies:Trolls and Troll archers", true, false, false, Color.darkGray,
                new int[]{15, 16, 15, 16, 15, 15, 15, 16, 15, 16, 16, 16, 0}, new int[]{300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300});
        Map tulivuori3 = new Map(505, 227, "Volcano", "Enemies:Trolls and Troll archers\nBoss:Dragon", true, false, false, Color.darkGray,
                new int[]{15, 16, 15, 15, 15, 16, 16, 16, 15, 16, 15, 16, 15, 15, 16, 5, 0}, new int[]{300, 300, 300, 300, 300, 300, 160, 300, 150, 300, 300, 300, 150, 300, 160, 150});

        Map linna1 = new Map(240, 325, "Road to Castle", "Enemies:Soldiers and Mages", true, false, false, new Color(0, 102, 0),
                new int[]{1, 2, 2, 1, 1, 2, 1, 2, 1, 1, 0}, new int[]{300, 300, 300, 300, 300, 300, 300, 300, 300, 300});
        Map linna2 = new Map(313, 273, "Military Camp", "Enemies:Soldiers,Mages,Elite Soldiers\n and Elite Mages", true, false, false, new Color(0, 102, 0),
                new int[]{1, 1, 2, 2, 3, 2, 1, 4, 3, 1, 1, 2, 3, 2, 3, 4, 0}, new int[]{300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300});
        Map linna3 = new Map(258, 215, "Castle", "Enemies:Elite Soldiers and Elite Mages\nFinal Boss:Grand Wizard", true, false, false, new Color(0, 102, 0),
                new int[]{3, 4, 4, 3, 4, 4, 3, 3, 4, 3, 4, 4, 4, 3, 4, 3, 4, 3, 4, 17, 0}, new int[]{300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300});


        mapit.add(metsa1);
        mapit.add(metsa2);
        mapit.add(metsa3);
        mapit.add(saari1);
        mapit.add(saari2);
        mapit.add(saari3);
        mapit.add(saari4);
        mapit.add(jaa1);
        mapit.add(jaa2);
        mapit.add(jaa3);
        mapit.add(jaa4);
        mapit.add(tulivuori1);
        mapit.add(tulivuori2);
        mapit.add(tulivuori3);
        mapit.add(linna1);
        mapit.add(linna2);
        mapit.add(linna3);

        //int fireRate, double damage, double size, double speed, int level, int type, int fire, int ice, int lightning, int wind, String icon, String name
        Magic basic = new Magic(150, 60, 50, 1, 1, 1, 0, 0, 0, 0, "basic.png", "Magic Ball", "Fires one big magic ball.\nDamage:High\nFirerate:Low\nSize:Big");
        basic.setSelected(true);
        Magic shotgun = new Magic(150, 15, 25, 1, 1, 2, 0, 0, 0, 0, "shotgun.png", "Spreading Magic", "Fires 5 magic balls in cone.\nDamage:Medium\nFirerate:Low\nSize:Medium");
        Magic beam = new Magic(10, 4, 20, 1, 1, 3, 0, 0, 0, 0, "beam.png", "Magic Beam", "Fires magic balls with rapid firerate.\nDamage:Low\nFirerate:High\nSize:Small");
        Magic spray = new Magic(10, 5, 30, 1, 1, 4, 0, 0, 0, 0, "spray.png", "Magic Spray", "Sprays low accuracy magic balls with rapid firerate.\nMagic balls dissapear after while.\nDamage:Medium\nFirerate:High\nSize:Small ");
        //Magic taika = new Magic();

        taijat.add(basic);
        taijat.add(shotgun);
        taijat.add(beam);
        taijat.add(spray);


        Upgrade damageU = new Upgrade("Damage", "damage.png", "Increase damage.", 1, 0, true);
        Upgrade firerateU = new Upgrade("Firerate", "firerate.png", "Increase firerate", 2, 0, true);
        Upgrade sizeU = new Upgrade("Size", "size.png", "Increase bullet size and improve elemetal effects.", 3, 0, true);
        Upgrade fireU = new Upgrade("Fire", "tuli.png", "Set enemies on fire. Increase fire damage", 4, 0, false);
        Upgrade iceU = new Upgrade("Ice", "ice.png", "Reduce enemy movementspeed. Improve slow", 5, 0, false);
        Upgrade lightningU = new Upgrade("Lightning", "salama.png", "Deal damage to nearby enemies. Icrease area of effect damage", 6, 0, false);
        Upgrade windU = new Upgrade("Wind", "wind.png", "knock back enemies. Increase knock back distance", 7, 0, false);

        upgradet.add(damageU);
        upgradet.add(firerateU);
        upgradet.add(sizeU);
        upgradet.add(fireU);
        upgradet.add(iceU);
        upgradet.add(lightningU);
        upgradet.add(windU);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        // g2d.translate(this.getWidth()/2, this.getHeight()/2);
        // g2d.rotate(angle);

        ImageIcon player = new ImageIcon(polku + pelaaja);
        /* ImageIcon enemy = new ImageIcon(polku + vihu);
         ImageIcon enemyfire = new ImageIcon(polku + vihufire);
         ImageIcon enemy2 = new ImageIcon(polku + vihu2);
         ImageIcon enemyfire2 = new ImageIcon(polku + vihufire2);
         ImageIcon enemy3 = new ImageIcon(polku + vihu3);
         ImageIcon enemyfire3 = new ImageIcon(polku + vihufire3);
         ImageIcon enemy4 = new ImageIcon(polku + vihu4);
         ImageIcon enemyfire4 = new ImageIcon(polku + vihufire4);
         ImageIcon enemy5 = new ImageIcon(polku + boss);
         ImageIcon enemyfire5 = new ImageIcon(polku + bossfire);
         ImageIcon enemy6 = new ImageIcon(polku+vihu6);
         ImageIcon enemyfire6 = new ImageIcon(polku+vihufire6);*/

        ImageIcon explosion = new ImageIcon(polku + rajahdys);
        ImageIcon aim = new ImageIcon(polku + tahtain);
        ImageIcon salama = new ImageIcon(polku + upgradet.get(0).getIcon());
        ImageIcon tuli = new ImageIcon(polku + upgradet.get(1).getIcon());
        ImageIcon jaa = new ImageIcon(polku + upgradet.get(2).getIcon());
        ImageIcon kivi = new ImageIcon(polku + upgradet.get(3).getIcon());
        ImageIcon tuuli = new ImageIcon(polku + upgradet.get(4).getIcon());

        ImageIcon mappi = new ImageIcon(polku + "velhomappi.png");
        ImageIcon lock = new ImageIcon(polku + "locked.png");

        g.setColor(Color.BLACK);
        g.drawString("Level: " + level, 50, 50);
        g.drawString("Highscore: " + highscore, 150, 50);
        g.setColor(Color.YELLOW);
        g.drawString("Skillpoints: " + skillpoints, 250, 50);

        g.setColor(Color.RED);
        g.fillRect(550, 800, 10, -(int) cooldown);

        g2d.setColor(Color.RED);
        g2d.fill(new Rectangle.Double(10, this.getHeight() - 210, 20, 200));
        g2d.setColor(Color.GREEN);
        g2d.fill(new Rectangle.Double(10, this.getHeight() - 210, 20, (life / maxlife) * 200));
        g.drawString("" + (int) life, 10, this.getHeight() - 220);

        //--------------------------------------------------------------------------- 
        if (menu == true) {

            if (menuselect == 1) {
                g2d.setColor(Color.WHITE);
                g2d.fillRect(50, 80, 500, 610);
                // valitse taika tyyppi (normaali,shotgun,beam,spray)
                for (int i = 0; i < taijat.size(); i++) {
                    g2d.setColor(Color.BLACK);
                    ImageIcon image = new ImageIcon(polku + taijat.get(i).getIcon());
                    image.paintIcon(this, g, 300, taijat.get(i).getType() * 100);
                    g2d.drawString(taijat.get(i).getName(), 160, taijat.get(i).getType() * 95 + 50);
                    if (taijat.get(i).isSelected()) {
                        g2d.drawRect(150, taijat.get(i).getType() * 100 - 5, 300, 100);
                        //g2d.drawString(taijat.get(i).getDescription(), 150, 500);
                        g2d.drawString("description:", 80, 510);
                        drawString(g2d, taijat.get(i).getDescription(), 160, 500);
                        g2d.drawRect(150, 500, 300, 100);
                    }
                }
                g2d.setColor(Color.WHITE);
                g2d.fillRect(450, 700, 100, 100);
                g2d.setColor(Color.BLACK);
                g2d.drawString("Continue", 485, 755);

            } else if (menuselect == 2) {

                g2d.setColor(Color.WHITE);
                g2d.fillRect(50, 80, 500, 610);


                for (int i = 0; i < upgradet.size(); i++) {
                    g2d.setColor(Color.BLACK);
                    if (upgradet.get(i).isUnlocked()) {
                        ImageIcon image = new ImageIcon(polku + upgradet.get(i).getIcon());
                        image.paintIcon(this, g, 60, upgradet.get(i).getType() * 85);
                    } else {
                        lock.paintIcon(this, g, 60, upgradet.get(i).getType() * 85);
                    }


                    g2d.drawString(upgradet.get(i).getName(), 160, upgradet.get(i).getType() * 85 + 50);

                    for (int j = 0; j < upgradet.get(i).getPoints(); j++) {
                        g2d.fillRect(240 + (j * 10), upgradet.get(i).getType() * 85 + 40, 8, 10);
                    }
                    g2d.drawRect(350, upgradet.get(i).getType() * 85 + 5, 100, 80);
                    g2d.setColor(Color.GREEN);
                    if (upgradet.get(i).getPoints() < 10 && upgradet.get(i).getPoints() + 1 <= skillpoints) {
                        g2d.drawString("Upgrade", 375, upgradet.get(i).getType() * 85 + 50);
                    } else if (upgradet.get(i).getPoints() == 10) {
                        g2d.setColor(Color.BLUE);
                        //System.out.println(upgradet.get(i).getType());
                        g2d.drawString("Max", 375, upgradet.get(i).getType() * 85 + 50);
                    } else {
                        g2d.setColor(Color.RED);
                        drawString(g2d, "Not enought\n skillpoints", 375, upgradet.get(i).getType() * 85 + 25);
                    }

                    /* if(taijat.get(i).isOstettu()==false){
                     taijat.get(i).setIcon("locked.png");
                     }else{
                     taijat.get(i).setIcon(icons[i]);
                     }*/
                }
                g2d.setColor(Color.WHITE);
                g2d.fillRect(450, 700, 100, 100);
                g2d.setColor(Color.BLACK);
                g2d.drawString("Continue", 485, 755);

            } else if (menuselect == 3) {



                for (int i = 0; i < mapit.size(); i++) {
                    if (mapit.get(i).isSelected()) {
                        g2d.setColor(Color.ORANGE);
                    } else if (mapit.get(i).isCleared()) {
                        g2d.setColor(Color.GRAY);
                    } else if (mapit.get(i).isLocked()) {
                        g2d.setColor(Color.RED);
                    } else {
                        g2d.setColor(Color.GREEN);
                    }
                    g2d.fillRect(mapit.get(i).getX(), mapit.get(i).getY(), 40, 40); //-----------------------------------------------< MAPIT
                }
                mappi.paintIcon(this, g, 0, 0);

                g2d.setColor(Color.WHITE);
                g2d.fillRect(320, 650, 250, 200);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(330, 780, 100, 60);
                g2d.drawString("Skill menu", 350, 815);
                g2d.drawRect(455, 780, 100, 60);
                g2d.drawString("Start", 490, 815);
                for (int i = 0; i < mapit.size(); i++) {
                    if (mapit.get(i).isSelected()) {
                        g2d.drawString(mapit.get(i).getNimi(), 330, 665);
                        drawString(g, mapit.get(i).getDescription(), 330, 675);
                        if (mapit.get(i).isLocked()) {
                            g2d.drawString("This map is locked!", 330, 750);
                        } else if (mapit.get(i).isCleared()) {
                            g2d.drawString("This map is cleared!", 330, 750);
                        }
                    }
                }

            }


            //---------------------------------------------------------------------------     
        } else {
            g2d.setColor(Color.BLACK);
            g2d.fillOval((int) x, (int) y, (int) pLeveys, (int) pKorkeus);
            player.paintIcon(this, g, (int) x - fix, (int) y - fiy);

            //g2d.setColor(Color.BLUE);
            //g2d.fill(new Rectangle.Double(x , y , pLeveys, pKorkeus));

            g2d.setColor(Color.BLACK);
            g2d.draw(new Line2D.Double(x + (pLeveys / 2), y + (pKorkeus / 2), mx, my));

            for (int i = 0; i < luodit.size(); i++) {
                luodit.get(i).draw(g);
            }

            g2d.setColor(Color.BLACK);
            for (int i = 0; i < monsterit.size(); i++) {

                g2d.drawString("" + (int) monsterit.get(i).getLife(), (int) monsterit.get(i).getX() + 30, (int) monsterit.get(i).getY() - 65);


                if (monsterit.get(i).getShoot() < 50) {
                    g2d.fillOval((int) monsterit.get(i).getX(), (int) monsterit.get(i).getY(), 75, 75);
                    ImageIcon image = new ImageIcon(polku + monsterit.get(i).getKuva1());
                    image.paintIcon(this, g, (int) monsterit.get(i).getX() - monsterit.get(i).getKeskitysX(), (int) monsterit.get(i).getY() - monsterit.get(i).getKeskitysY());
                } else {
                    g2d.fillOval((int) monsterit.get(i).getX(), (int) monsterit.get(i).getY(), 75, 75);
                    ImageIcon image = new ImageIcon(polku + monsterit.get(i).getKuva2());
                    image.paintIcon(this, g, (int) monsterit.get(i).getX() - monsterit.get(i).getKeskitysX(), (int) monsterit.get(i).getY() - monsterit.get(i).getKeskitysY());

                }
            }

            for (int i = 0; i < vihuluodit.size(); i++) {
                vihuluodit.get(i).draw(g);
            }

            for (int i = 0; i < tehosteet.size(); i++) {
                tehosteet.get(i).draw(g);
            }

            /* g.setColor(Color.YELLOW);
             g.fillOval(-50 + (magic * 100), 770, 90, 90);

             salama.paintIcon(this, g, 50, 770);
             tuli.paintIcon(this, g, 150, 770);
             jaa.paintIcon(this, g, 250, 770);
             kivi.paintIcon(this, g, 350, 770);
             tuuli.paintIcon(this, g, 450, 770);*/
        }

        aim.paintIcon(this, g, (int) mx - 25, (int) my - 25);

    }

    public void actionPerformed(ActionEvent e) {

        if (menu == true) {
            // jotain menuun
        } else {

            if (firing == true) {
                addBullet();
            }

            // x= (this.getWidth()/2);
            // y= (this.getHeight()/2);

            for (int j = 0; j < monsterit.size(); j++) {

                monsterit.get(j).liiku(this.getHeight(), this.getWidth(), x + (pLeveys / 2), y + (pKorkeus / 2));
                if (monsterit.get(j).getShoot() < 0) {
                    if (monsterit.get(j).getType() == 1 || monsterit.get(j).getType() == 3 || monsterit.get(j).getType() == 6
                            || monsterit.get(j).getType() == 7 || monsterit.get(j).getType() == 15) {
                        addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 100, 10, 2);
                    } else if (monsterit.get(j).getType() == 2) {
                        addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 10, 1);
                    } else if (monsterit.get(j).getType() == 4) {
                        addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 5, 4);
                    } else if (monsterit.get(j).getType() == 5) {
                        if (monsterit.get(j).getCombo() < 10) {
                            addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 5, 5);
                        }
                        monsterit.get(j).setCombo(monsterit.get(j).getCombo() + 1);
                        monsterit.get(j).setShoot(monsterit.get(j).getFirerate());
                        if (monsterit.get(j).getCombo() >= 10) {
                            monsterit.get(j).setShoot(300);
                            monsterit.get(j).setCombo(0);
                        }
                    } else if (monsterit.get(j).getType() == 8) {
                        addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 5, 6);
                    } else if (monsterit.get(j).getType() == 10) {
                        addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 10, 7);
                    } else if (monsterit.get(j).getType() == 11) {
                        if (monsterit.get(j).getCombo() == 9) {
                            addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 200, 20, 8);
                        } else {
                            addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 5, 9);
                        }
                        monsterit.get(j).setCombo(monsterit.get(j).getCombo() + 1);
                    } else if (monsterit.get(j).getType() == 13) {
                        addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 10, 13);
                    } else if (monsterit.get(j).getType() == 14) {
                        addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 400, 10, 14);
                        monsterit.get(j).setCombo(monsterit.get(j).getCombo() + 1);
                        monsterit.get(j).setShoot(monsterit.get(j).getFirerate());
                        if (monsterit.get(j).getCombo() >= 10) {
                            monsterit.get(j).setShoot(300);
                            monsterit.get(j).setCombo(0);
                        }
                    } else if (monsterit.get(j).getType() == 16) {
                        addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 5, 16);
                    } else if (monsterit.get(j).getType() == 17) {
                        if (monsterit.get(j).getCombo() == 0) {
                            addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 300, 10, 8);
                            monsterit.get(j).setShoot(200);
                        } else if (monsterit.get(j).getCombo() > 0 && monsterit.get(j).getCombo() < 10) {
                            addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 10, 5);
                            monsterit.get(j).setShoot(20);
                        } else if (monsterit.get(j).getCombo() == 10) {
                            addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 1000, 10, 6);
                            monsterit.get(j).setShoot(200);
                        } else {
                            addEnemyBullet(monsterit.get(j).getX(), monsterit.get(j).getY(), 300, 10, 14);
                            monsterit.get(j).setShoot(20);
                        }
                        monsterit.get(j).setCombo(monsterit.get(j).getCombo() + 1);
                        if (monsterit.get(j).getCombo() >= 20) {
                            monsterit.get(j).setShoot(200);
                            monsterit.get(j).setCombo(0);
                        }
                    } else if (monsterit.get(j).getType() == 18) {
                        monsterit.add(new Monster(monsterit.get(j).getX(), monsterit.get(j).getY(), 0, 0, enemySpeed * 2, enemyLife * 0.4 + (level * 2), enemyFireRate, "vihubee1.png", "vihubee2.png", 35, 60, 3, 1, Color.BLACK));
                    }
                    if (monsterit.get(j).getType() != 17 && monsterit.get(j).getType() != 5 && monsterit.get(j).getType() != 14) {
                        monsterit.get(j).setShoot(monsterit.get(j).getFirerate());
                    }
                }

                /*if (monsterit.get(j).getCombo() >= 10) {
                 monsterit.get(j).setShoot(300);
                 monsterit.get(j).setCombo(0);
                 }*/

                if (monsterit.get(j).getY() > this.getHeight()) {
                    monsterit.remove(j);
                }
            }

            for (int j = 0; j < monsterit.size(); j++) {
                if (monsterit.get(j).getLife() <= 0) {
                    pisteet++;
                    //skillpoints++;
                    monsterit.remove(j);
                }
            }

            // System.out.println(luodit.size());

//------------------------------------------------------------------------

            for (int i = 0; i < luodit.size(); i++) {

                luodit.get(i).liiku(this.getHeight(), this.getWidth());

                for (int j = 0; j < monsterit.size(); j++) {

                    if (Math.sqrt(Math.pow(Math.abs(luodit.get(i).getX() + (luodit.get(i).getpLeveys() / 2) - (monsterit.get(j).getX() + (monsterit.get(j).getpLeveys() / 2))), 2)
                            + Math.pow(Math.abs(luodit.get(i).getY() + (luodit.get(i).getpLeveys() / 2) - (monsterit.get(j).getY() + (monsterit.get(j).getpLeveys() / 2))), 2))
                            < (luodit.get(i).getpLeveys() / 2) + (monsterit.get(j).getpLeveys() / 2)) {
                        /*if (luodit.get(i).getX() <= monsterit.get(j).getX() + monsterit.get(j).getpLeveys()
                         && luodit.get(i).getX() >= monsterit.get(j).getX()
                         && luodit.get(i).getY() <= monsterit.get(j).getY() + monsterit.get(j).getpKorkeus()
                         && luodit.get(i).getY() >= monsterit.get(j).getY()) {*/

                        if (audio < 1) {
                            hitSound(3);
                            audio = 10;
                        }

                        monsterit.get(j).takeDamage((int) luodit.get(i).getDamage());


                        if (taika.getIce() > 0) {
                            monsterit.get(j).setIceLevel(taika.getIce());
                            monsterit.get(j).setIceDuratio((int) taika.getSize() * 5);
                            //System.out.println(monsterit.get(j).getSpeed());
                        }
                        if (taika.getFire() > 0) {
                            monsterit.get(j).setBurnDuratio((int) taika.getSize() * 20);
                            monsterit.get(j).setBurnDamage((int) taika.getFire());
                            //System.out.println(taijat.get(3).getLevel());
                        }
                        if (taika.getWind() > 0) {
                            monsterit.get(j).push(luodit.get(i).getVelx() * (luodit.get(i).getDuration() * (taika.getSize() * taika.getWind() / 1000)),
                                    luodit.get(i).getVely() * (luodit.get(i).getDuration() * (taika.getSize() * taika.getWind() / 1000)));
                        }

                        if (taika.getLightning() > 0) {
                            tehosteet.add(new Effects(monsterit.get(j).getX() - taika.getSize() * 2, monsterit.get(j).getY() - taika.getSize() * 2, 1, monsterit.get(j).getpLeveys() + (taika.getSize() * 4), monsterit.get(j).getpKorkeus() + (taika.getSize() * 4)));

                            for (int q = 0; q < monsterit.size(); q++) {
                                if (Math.sqrt(Math.pow(Math.abs(monsterit.get(j).getX() + (monsterit.get(j).getpLeveys() / 2) - (monsterit.get(q).getX() + (monsterit.get(q).getpLeveys() / 2))), 2)
                                        + Math.pow(Math.abs(monsterit.get(j).getY() + (monsterit.get(j).getpLeveys() / 2) - (monsterit.get(q).getY() + (monsterit.get(q).getpLeveys() / 2))), 2))
                                        < (taika.getSize() * 2) + (monsterit.get(q).getpLeveys() / 2)) {

                                    if (q != j) {
                                        System.out.println((luodit.get(i).getDamage() * ((double) taika.getLightning() / 10)));
                                        monsterit.get(q).takeDamage((luodit.get(i).getDamage() * ((double) taika.getLightning() / 10)));
                                        //public Effects(double x, double y, double x2, double y2, int type) {
                                        tehosteet.add(new Effects(monsterit.get(j).getX() + (monsterit.get(j).getpLeveys() / 2), monsterit.get(j).getY() + (monsterit.get(j).getpKorkeus() / 2),
                                                monsterit.get(q).getX() + (monsterit.get(q).getpLeveys() / 2), monsterit.get(q).getY() + (monsterit.get(q).getpKorkeus() / 2), 2));

                                    }

                                }
                            }


                        }


                        /*public EnemyBullet(double x, double y, double velx, double vely, double duration,
                         double pLeveys, double pKorkeus, double damage) {*/

                        if (monsterit.get(j).getType() == 3) {
                            vihuluodit.add(new EnemyBullet(luodit.get(i).getX(), luodit.get(i).getY(), luodit.get(i).getVelx() * -0.5, luodit.get(i).getVely() * -0.5, 500,
                                    luodit.get(i).getpLeveys() / 2, luodit.get(i).getpKorkeus() / 2, luodit.get(i).getDamage() * 0.1, 3, Color.ORANGE));
                        }

                        luodit.get(i).setDelete(true);

                    }

                    if (luodit.get(i).getDuration() < 0) {
                        luodit.get(i).setDelete(true);
                    }
                    if (monsterit.get(j).getLife() <= 0) {
                        //explosion();
                        pisteet++;
                        //skillpoints++;
                        //level++;
                        monsterit.remove(j);
                    }
                }
            }

            for (int i = 0; i < luodit.size(); i++) {
                if (luodit.get(i).isDelete() == true) {
                    luodit.remove(i);
                }
            }

            for (int i = 0; i < luodit.size(); i++) {
                if (luodit.get(i).getX() > this.getWidth() || luodit.get(i).getX() < 0
                        || luodit.get(i).getY() > this.getHeight() || luodit.get(i).getY() < 0) {
                    luodit.remove(i);
                }
            }
            //---------------------------------------------------------------------------------

            for (int i = 0; i < vihuluodit.size(); i++) {

                vihuluodit.get(i).liiku(this.getHeight(), this.getWidth());

                if (Math.sqrt(Math.pow(Math.abs(vihuluodit.get(i).getX() + (vihuluodit.get(i).getpLeveys() / 2) - (x + (pLeveys / 2))), 2)
                        + Math.pow(Math.abs(vihuluodit.get(i).getY() + (vihuluodit.get(i).getpLeveys() / 2) - (y + (pLeveys / 2))), 2)) < (pLeveys / 2) + (vihuluodit.get(i).getpLeveys() / 2)) {

                    if (vihuluodit.get(i).getBulletType() == 5 || vihuluodit.get(i).getBulletType() == 16) {
                        fire = 300;

                    } else if (vihuluodit.get(i).getBulletType() == 6) {
                        x = x + vihuluodit.get(i).getVelx() * 30;
                        y = y + vihuluodit.get(i).getVely() * 30;
                        hitSound(2);
                        //knock back
                    } else if (vihuluodit.get(i).getBulletType() == 14 || vihuluodit.get(i).getBulletType() == 13) {
                        ice = 100;
                        hitSound(1);
                    } else {
                        hitSound(3);
                    }


                    //hitSound();
                    life -= vihuluodit.get(i).getDamage();
                    vihuluodit.remove(i);
                }
            }



            if (fire > 0) {
                life -= 0.03;
                fire--;
            }

            if (ice > 0) {
                speed = 0.5;
                ice--;
            } else {
                speed = maxspeed;
            }


            for (int i = 0; i < vihuluodit.size(); i++) {
                if (vihuluodit.get(i).getX() > this.getWidth() || vihuluodit.get(i).getX() < 0
                        || vihuluodit.get(i).getY() > this.getHeight() || vihuluodit.get(i).getY() < 0) {
                    vihuluodit.remove(i);
                }
            }

            for (int i = 0; i < vihuluodit.size(); i++) {
                //System.out.println(vihuluodit.get(i).getDuration());
                if (vihuluodit.get(i).getBulletType() == 8 && vihuluodit.get(i).getDuration() < 5) {
                    vihuluodit.get(i).setpKorkeus(120);
                    vihuluodit.get(i).setpLeveys(120);
                    vihuluodit.get(i).setX(vihuluodit.get(i).getX() - 60);
                    vihuluodit.get(i).setY(vihuluodit.get(i).getY() - 60);
                    vihuluodit.get(i).setBulletType(7);
                }
                if (vihuluodit.get(i).getDuration() < 0) {
                    vihuluodit.remove(i);
                }
            }

            for (int i = 0; i < tehosteet.size(); i++) {
                tehosteet.get(i).reduceDuration();
            }

            for (int i = 0; i < tehosteet.size(); i++) {
                if (tehosteet.get(i).getDuration() < 0) {
                    tehosteet.remove(i);
                }
            }

//---------------------------------------------------------------------------

            if (up == true && right == true) {
                vely = -0.75 * speed;
                velx = 0.75 * speed;
            } else if (up == true && left == true) {
                vely = -0.75 * speed;
                velx = -0.75 * speed;
            } else if (down == true && right == true) {
                vely = 0.75 * speed;
                velx = 0.75 * speed;
            } else if (down == true && left == true) {
                vely = 0.75 * speed;
                velx = -0.75 * speed;
            } else if (up == true) {
                vely = -1.5 * speed;
                velx = 0;
            } else if (down == true) {
                vely = 1.5 * speed;
                velx = 0;
            } else if (right == true) {
                velx = 1.5 * speed;
                vely = 0;
            } else if (left == true) {
                velx = -1.5 * speed;
                vely = 0;
            } else {
                velx = 0;
                vely = 0;
            }

            //----------------------------------   

            if (life <= 0) {
                menu = true;
                tyhjenna();
                vuoro = 0;
                life = maxlife;
                setBackground(Color.darkGray);
            }

            //System.out.println(spawn);

            if (spawn == 0) {
                addEnemy();
            }


                x += velx;
                y += vely;


            if (x + pLeveys > this.getWidth()) {
                x = (this.getWidth() - pLeveys) - 1;
            }
            if (x < 0) {
                x = 1;
            }
            
            if (y < 0) {
                y = 0;
            }
            
            if(y+pKorkeus>this.getHeight()){
                y=this.getHeight()-pKorkeus;
            }

            spawn--;
            angle += 0.01;
            audio--;
            audiofire--;

            if (cooldown > -1) {
                cooldown--;
            }

            //if(mapit.get(selectedMap).getMonsters()==0){




            if (monsters[vuoro] == 0 && monsterit.isEmpty()) {
                tyhjenna();
                menu = true;
                mapit.get(selectedMap).setCleared(true);
                skillpoints += level;
                level++;
                unlockLevels();
                vuoro = 0;
                maxlife = 90+(level*10);
                life = maxlife;
                spawn = 50;
                setBackground(Color.darkGray);
            }



            velhonSuunta();
        }

        repaint();
    }

    public void velhonSuunta() {

        if (cooldown < 0) {

            // System.out.println(x-mx);

            if (Math.abs(y - my) > Math.abs(x - mx) && y > my) {
                pelaaja = "velho1.png";
                fiy = 130;
                fix = 30;
            } else if (Math.abs(y - my) > Math.abs(x - mx) && y < my) {
                pelaaja = "velho3.png";
                fiy = 120;
                fix = 50;
            } else if (x < mx) {
                pelaaja = "velho5.png";
                fiy = 130;
                fix = 40;
            } else {
                pelaaja = "velho7.png";
                fiy = 130;
                fix = 120;
            }
        }
    }

    public void addEnemy() {

        System.out.println("spawnataan vihu");

        double ranx = Math.random() * (this.getWidth() - pLeveys);
        double rany = 1;


        //int enemyType = levels[pysty][vaaka];
        int enemyType = monsters[vuoro];        // ----------------------------------------------------------                  <---------------täällä
        System.out.println(enemyType);
        //System.out.println(enemyType);

        double apux = 0, apuy = 0;
        double jakox, jakoy;

        // velx=(mx-x-(pLeveys/2));    
        // vely=(my-y-(pLeveys/2)); 

        xero = (x - ranx);
        yero = (y - rany);


        // luodit.add(new Bullet(x,y,velx,vely));

        if (xero < 0) {
            apux = xero * (-1);
        } else {
            apux = xero;
        }

        if (yero < 0) {
            apuy = yero * (-1);
        } else {
            apuy = yero;
        }

        jakox = (apux / (apuy + apux)) * enemySpeed;
        jakoy = (apuy / (apuy + apux)) * enemySpeed;
        //System.out.println(jakox + " " + jakoy);

        if (xero < 0) {
            jakox = jakox * (-1);
        }

        if (yero < 0) {
            jakoy = jakoy * (-1);
        }

        if (enemyType == 1) {
            System.out.println("spawnataan enemytype 1");
            //public Monster(double x, double y, double maxspeed, double life, double firerate, String kuva1, String kuva2, int movetype, int type) {
            monsterit.add(new Monster(ranx, rany, jakox, jakoy, enemySpeed, enemyLife + (level * 10), enemyFireRate, "vihu1.png", "vihu2.png", 35, 60, 3, 1, Color.BLACK));

            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 2) {
            monsterit.add(new Monster(ranx, rany, 0.5, 0.5, enemySpeed, enemyLife + (level * 5), enemyFireRate * 3 - (level * 10), "vihuvelho1.png", "vihuvelho2.png", 35, 60, 1, 2, Color.GREEN));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 3) {
            monsterit.add(new Monster(ranx, rany, jakox, jakoy, enemySpeed, enemyLife * 2 + (level * 10), enemyFireRate, "kilpi1.png", "kilpi2.png", 35, 60, 3, 3, Color.ORANGE)); //kilpi
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 4) {
            monsterit.add(new Monster(ranx, rany, 0.5, 0.5, enemySpeed, enemyLife * 2 + (level * 5), enemyFireRate * 4 - (level * 10), "vihuwizard1.png", "vihuwizard2.png", 35, 60, 1, 4, Color.CYAN));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 5) {
            monsterit.add(new Monster(ranx, rany, 0.5, 0.5, enemySpeed, enemyLife * 10 + (level * 100), 20, "dragon1.png", "dragon2.png", 35, 60, 1, 5, Color.RED));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 6) {
            monsterit.add(new Monster(ranx, rany, jakox, jakoy, enemySpeed, enemyLife + (level * 10), enemyFireRate, "vihuboar1.png", "vihuboar2.png", 35, 60, 3, 1, Color.BLACK));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 7) {
            monsterit.add(new Monster(ranx, rany, jakox, jakoy, enemySpeed * 2, enemyLife * 0.4 + (level * 2), enemyFireRate, "vihubee1.png", "vihubee2.png", 35, 60, 3, 1, Color.BLACK));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 8) {
            monsterit.add(new Monster(ranx, rany, 0.5, 0.5, enemySpeed * 0.5, enemyLife * 10 + (level * 100), 300, "bossbird1.png", "bossbird2.png", 100, 60, 1, 8, Color.GRAY));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 9) {
            monsterit.add(new Monster(ranx, rany, jakox, jakoy, enemySpeed, enemyLife + (level * 10), enemyFireRate, "rapu1.png", "rapu2.png", 40, 50, 3, 1, Color.BLACK));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 10) {
            monsterit.add(new Monster(ranx, rany, jakox, jakoy, enemySpeed, enemyLife + (level * 5), enemyFireRate * 3 - (level * 10), "lokki1.png", "lokki2.png", 85, 30, 1, 10, Color.WHITE));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 11) {
            monsterit.add(new Monster(ranx, rany, 0.5, 0.5, enemySpeed * 2, enemyLife * 10 + (level * 100), 100, "bosspilvi1.png", "bosspilvi2.png", 85, 60, 1, 11, Color.BLUE));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 12) {
            monsterit.add(new Monster(ranx, rany, jakox, jakoy, enemySpeed, enemyLife + (level * 10), enemyFireRate, "yeti1.png", "yeti2.png", 50, 120, 3, 1, Color.BLACK));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 13) {
            monsterit.add(new Monster(ranx, rany, 0.5, 0.5, enemySpeed, enemyLife + (level * 5), enemyFireRate * 3 - (level * 10), "yetiranged1.png", "yetiranged2.png", 50, 120, 1, 13, Color.CYAN));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 14) {
            monsterit.add(new Monster(ranx, rany, 0.5, 0.5, enemySpeed, enemyLife * 10 + (level * 100), 50, "bosslumiukko2.png", "bosslumiukko1.png", 50, 60, 1, 14, Color.CYAN));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 15) {
            monsterit.add(new Monster(ranx, rany, jakox, jakoy, enemySpeed, enemyLife + (level * 10), enemyFireRate, "orkki1.png", "orkki2.png", 50, 165, 3, 1, Color.CYAN));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 16) {
            monsterit.add(new Monster(ranx, rany, jakox, jakoy, enemySpeed, enemyLife + (level * 5), enemyFireRate * 3 - (level * 10), "rangedorkki1.png", "rangedorkki2.png", 30, 120, 1, 16, Color.RED));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 17) {
            monsterit.add(new Monster(ranx, rany, 0.5, 0.5, enemySpeed, enemyLife * 20 + (level * 100), 20, "finalboss2.png", "finalboss1.png", 60, 170, 1, 17, Color.RED));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } else if (enemyType == 18) {
            monsterit.add(new Monster(ranx, rany, 0, 0.5, enemySpeed, enemyLife * 2 + (level * 10), 500, "beehive.png", "beehive.png", 60, 30, 1, 18, Color.RED));
            spawn = spawnTimes[vuoro];
            vuoro++;
        } /*else if (enemyType == 0 && monsterit.size() == 0) {
         menu = true;
         vuoro = 0;
         }*/




    }

    public void addEnemyBullet(double ranx, double rany, double duration, double damage, int bulletType) {

        double apux = 0, apuy = 0;
        double jakox, jakoy;

        xero = ((x + (pLeveys / 2)) - (ranx + (pLeveys / 2)));
        yero = ((y + (pKorkeus / 2)) - (rany + (pKorkeus / 2)));
        // luodit.add(new Bullet(x,y,velx,vely));

        if (xero < 0) {
            apux = xero * (-1);
        } else {
            apux = xero;
        }

        if (yero < 0) {
            apuy = yero * (-1);
        } else {
            apuy = yero;
        }
        jakox = (apux / (apuy + apux)) * enemyBulletSpeed;
        jakoy = (apuy / (apuy + apux)) * enemyBulletSpeed;
        //System.out.println(jakox + " " + jakoy);
        if (xero < 0) {
            jakox = jakox * (-1);
        }
        if (yero < 0) {
            jakoy = jakoy * (-1);
        }
        //luodit.add(new Bullet(x+(pLeveys/2), y+(pKorkeus/2), jakox-(jakoy*0.2), jakoy+(jakox*0.2),20,20,magic, taijat.get(3).getDamage()));
        if (bulletType == 4) {
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox, jakoy, duration, 30, 30, damage, bulletType, Color.CYAN));
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox - (jakoy * 0.2), jakoy + (jakox * 0.5), duration, 30, 30, damage, bulletType, Color.CYAN));
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox + (jakoy * 0.2), jakoy - (jakox * 0.5), duration, 30, 30, damage, bulletType, Color.CYAN));
        } else if (bulletType == 5) {
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox, jakoy, duration, 40, 40, damage, bulletType, Color.RED));
        } else if (bulletType == 7) {
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox * 1.25, jakoy * 1.25, duration, 20, 20, damage, bulletType, Color.WHITE));
        } else if (bulletType == 6) {
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox, jakoy, duration, 30, 30, damage, bulletType, Color.GRAY));
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox - (jakoy * 0.4), jakoy + (jakox * 0.4), duration, 30, 30, damage, bulletType, Color.GRAY));
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox + (jakoy * 0.4), jakoy - (jakox * 0.4), duration, 30, 30, damage, bulletType, Color.GRAY));
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox - (jakoy * 0.8), jakoy + (jakox * 0.8), duration, 30, 30, damage, bulletType, Color.GRAY));
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox + (jakoy * 0.8), jakoy - (jakox * 0.8), duration, 30, 30, damage, bulletType, Color.GRAY));
        } else if (bulletType == 8) {
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox, jakoy, (Math.sqrt(Math.pow(xero, 2) + Math.pow(yero, 2)) / 2) + (pLeveys / 2), 30, 30, damage, bulletType, Color.YELLOW));

        } else if (bulletType == 9) {
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), 0, 2, duration, 30, 30, damage, bulletType, Color.BLUE));
        } else if (bulletType == 13) {
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox, jakoy, duration, 40, 40, damage, bulletType, Color.CYAN));
        } else if (bulletType == 14) {
            double rando = (double) (Math.random() * 2 - 1);
            if ((jakox > 0 && jakoy > 0) || (jakox < 0 && jakoy < 0)) {
                vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox + (rando), jakoy - (rando), duration, 40, 40, damage, bulletType, Color.CYAN));
            } else {
                vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox + (rando), jakoy + (rando), duration, 40, 40, damage, bulletType, Color.CYAN));
            }

            //vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox + rando, jakoy - rando, duration, 40, 40, damage, bulletType, Color.CYAN));
        } else if (bulletType == 16) {
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox, jakoy, duration, 40, 40, damage, bulletType, Color.RED));
        } else {
            vihuluodit.add(new EnemyBullet(ranx + (pLeveys / 2), rany + (pKorkeus / 2), jakox, jakoy, duration, 40, 40, damage, bulletType, Color.CYAN));
        }

    }

    public void addBullet() {

        if (cooldown < 0) {
            double apux = 0, apuy = 0;
            double jakox, jakoy;


            // velx=(mx-x-(pLeveys/2));    
            // vely=(my-y-(pLeveys/2)); 

            xero = (mx - (x + (pLeveys / 2)));
            yero = (my - (y + (pKorkeus / 2)));

            // luodit.add(new Bullet(x,y,velx,vely));


            if (xero < 0) {
                apux = xero * (-1);
            } else {
                apux = xero;
            }

            if (yero < 0) {
                apuy = yero * (-1);
            } else {
                apuy = yero;
            }

            jakox = (apux / (apuy + apux)) * bulletSpeed;
            jakoy = (apuy / (apuy + apux)) * bulletSpeed;


            //System.out.println(jakox + " " + jakoy);

            if (xero < 0) {
                jakox = jakox * (-1);
            }

            if (yero < 0) {
                jakoy = jakoy * (-1);
            }

            //System.out.println("jakox:"+jakox+" jakoy:"+jakoy);
            double taikaSize = taika.getSize();

            if (taika.getType() == 1 || taika.getType() == 3) {
                //luodit.add(new Bullet(x + (pLeveys / 2) - taikaSize / 2, y + (pKorkeus / 2) - taikaSize / 2, jakox * (1 - (taijat.get(0).getLevel()) * 0.1), jakoy * (1 - (taijat.get(0).getLevel()) * 0.1), taikaSize, taikaSize, taika.getDamage(), 200));
                luodit.add(new Bullet(x + (pLeveys / 2) - taikaSize / 2, y + (pKorkeus / 2) - taikaSize / 2, jakox, jakoy, taikaSize, taikaSize, taika.getDamage(), 200));
                cooldown = taika.getFireRate();
                shotsFired(3);

            } else if (taika.getType() == 4) {
                double rando = (double) (Math.random() * 2 - 1);

                if ((jakox > 0 && jakoy > 0) || (jakox < 0 && jakoy < 0)) {
                    luodit.add(new Bullet(x + (pLeveys / 2) - taikaSize / 2, y + (pKorkeus / 2) - taikaSize / 2, jakox + (rando), jakoy - (rando), taikaSize, taikaSize, taika.getDamage(), 80));
                } else {
                    luodit.add(new Bullet(x + (pLeveys / 2) - taikaSize / 2, y + (pKorkeus / 2) - taikaSize / 2, jakox + (rando), jakoy + (rando), taikaSize, taikaSize, taika.getDamage(), 80));
                }


                cooldown = taika.getFireRate();
                shotsFired(3);
            } else if (taika.getType() == 2) {
                //jakox+(jakoy*0.2), jakoy-(jakox*0.2
                luodit.add(new Bullet(x + (pLeveys / 2) - taikaSize / 2, y + (pKorkeus / 2) - taikaSize / 2, jakox, jakoy, taikaSize, taikaSize, taika.getDamage(), 200));
                luodit.add(new Bullet(x + (pLeveys / 2) - taikaSize / 2, y + (pKorkeus / 2) - taikaSize / 2, jakox + (jakoy * 0.1), jakoy - (jakox * 0.1), taikaSize, taikaSize, taika.getDamage(), 200));
                luodit.add(new Bullet(x + (pLeveys / 2) - taikaSize / 2, y + (pKorkeus / 2) - taikaSize / 2, jakox - (jakoy * 0.1), jakoy + (jakox * 0.1), taikaSize, taikaSize, taika.getDamage(), 200));
                luodit.add(new Bullet(x + (pLeveys / 2) - taikaSize / 2, y + (pKorkeus / 2) - taikaSize / 2, jakox + (jakoy * 0.2), jakoy - (jakox * 0.2), taikaSize, taikaSize, taika.getDamage(), 200));
                luodit.add(new Bullet(x + (pLeveys / 2) - taikaSize / 2, y + (pKorkeus / 2) - taikaSize / 2, jakox - (jakoy * 0.2), jakoy + (jakox * 0.2), taikaSize, taikaSize, taika.getDamage(), 200));
                cooldown = taika.getFireRate();
                shotsFired(3);
            }

            if (Math.abs(y - my) > Math.abs(x - mx) && y > my) {
                pelaaja = "velho2.png";
                fiy = 130;
                fix = 30;
            } else if (Math.abs(y - my) > Math.abs(x - mx) && y < my) {
                pelaaja = "velho4.png";
                fiy = 120;
                fix = 50;
            } else if (x < mx) {
                pelaaja = "velho6.png";
                fiy = 130;
                fix = 40;
            } else {
                pelaaja = "velho8.png";
                fiy = 130;
                fix = 120;
            }


        }

    }

    public void unlockLevels() {

        if (mapit.get(0).isCleared()) {
            mapit.get(1).setLocked(false);
        }
        if (mapit.get(1).isCleared()) {
            mapit.get(2).setLocked(false);
        }
        if (mapit.get(2).isCleared()) {
            upgradet.get(6).setUnlocked(true);
            //windUnlocked=true;
        }

        if (mapit.get(3).isCleared()) {
            mapit.get(4).setLocked(false);
        }
        if (mapit.get(4).isCleared()) {
            mapit.get(5).setLocked(false);
        }
        if (mapit.get(5).isCleared()) {
            mapit.get(6).setLocked(false);
        }
        if (mapit.get(6).isCleared()) {
            upgradet.get(5).setUnlocked(true);
            //lightningUnlocked=true;
        }

        if (mapit.get(7).isCleared()) {
            mapit.get(8).setLocked(false);
        }
        if (mapit.get(8).isCleared()) {
            mapit.get(9).setLocked(false);
        }
        if (mapit.get(9).isCleared()) {
            mapit.get(10).setLocked(false);
        }
        if (mapit.get(10).isCleared()) {
            upgradet.get(4).setUnlocked(true);
            //iceUnlocked=true;
        }

        if (mapit.get(11).isCleared()) {
            mapit.get(12).setLocked(false);
        }
        if (mapit.get(12).isCleared()) {
            mapit.get(13).setLocked(false);
        }
        if (mapit.get(13).isCleared()) {
            upgradet.get(3).setUnlocked(true);
            //fireUnlocked=true;
        }

        if (mapit.get(2).isCleared() && mapit.get(6).isCleared()
                && mapit.get(10).isCleared() && mapit.get(13).isCleared()) {
            mapit.get(14).setLocked(false);
        }
        if (mapit.get(14).isCleared()) {
            mapit.get(15).setLocked(false);
        }
        if (mapit.get(15).isCleared()) {
            mapit.get(16).setLocked(false);
        }

    }

    public void tyhjenna() {

        luodit.clear();
        vihuluodit.clear();
        monsterit.clear();

    }

    public void reset() {

        if (pisteet > highscore) {
            highscore = pisteet;
        }
        skillpoints = 5;
        x = 300;
        y = 500;
        velx = 0;
        vely = 0;
        pisteet = 0;
        spawn = 500;
        enemyLife = 50;
        life = 100;
        maxlife = 100;
        hpCost = 10;
        pysty = 0;
        vaaka = 0;
        monsterit.removeAll(monsterit);
        luodit.removeAll(luodit);
        vihuluodit.removeAll(vihuluodit);
        menu = true;

    }

    public void cheat() {
        for (int i = 0; i < mapit.size(); i++) {
            mapit.get(i).setLocked(false);
        }
        for (int i = 0; i < upgradet.size(); i++) {
            upgradet.get(i).setUnlocked(true);
        }
        level = 17;
        skillpoints = 154;
    }

    public void stop() {
        velx = 0;
        vely = 0;
    }

    public void painovoima() {
        vely += 0.02;
    }

    public void shotsFired(int sound) {

        if (audiofire < 1) {
            try {
                Clip clip = AudioSystem.getClip();
                if (sound == 1) {
                    clip.open(AudioSystem.getAudioInputStream(new File(polku + "lightning.wav")));
                } else if (sound == 2) {
                    clip.open(AudioSystem.getAudioInputStream(new File(polku + "burn.wav")));
                } else if (sound == 3) {
                    clip.open(AudioSystem.getAudioInputStream(new File(polku + "icewind.wav")));
                } else if (sound == 4) {
                    clip.open(AudioSystem.getAudioInputStream(new File(polku + "earthquake.wav")));
                } else if (sound == 5) {
                    clip.open(AudioSystem.getAudioInputStream(new File(polku + "windy.wav")));
                }
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            audiofire = 100;
        }



    }

    public void hitSound(int sound) {

        try {
            Clip clip = AudioSystem.getClip();

            if (sound == 1) {
                clip.open(AudioSystem.getAudioInputStream(new File(polku + "electricshock.wav")));
            } else if (sound == 2) {
                clip.open(AudioSystem.getAudioInputStream(new File(polku + "iced.wav")));
            } else if (sound == 3) {
                clip.open(AudioSystem.getAudioInputStream(new File(polku + "rock.wav")));
            }
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }

    }

    public void explosion() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(polku + "Bomb.wav")));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }

    public void mouseMoved(MouseEvent e) {

        int koordX = e.getX();
        int koordY = e.getY();

        mx = koordX;
        my = koordY;
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        firing = false;
    }

    public void mousePressed(MouseEvent e) {
        int koordX = e.getX();
        int koordY = e.getY();

        if (menu == false) {
            firing = true;
        } else {


            if (menuselect == 1) {

                for (int i = 0; i < taijat.size(); i++) {
                    if (koordX > 150 && koordX < 450 && koordY > taijat.get(i).getType() * 100 - 5 && koordY < upgradet.get(i).getType() * 100 + 105) {
                        //g2d.drawRect(150,taijat.get(i).getType()*100-5,300,100);
                        for (int j = 0; j < taijat.size(); j++) {
                            taijat.get(j).setSelected(false);
                        }
                        taijat.get(i).setSelected(true);
                    }
                }

                if (koordX > 450 && koordX < 550 && koordY > 700 && koordY < 800) {

                    for (int i = 0; i < taijat.size(); i++) {
                        if (taijat.get(i).isSelected() == true) {

                            taika.setDamage(taijat.get(i).getDamage());
                            taika.setFireRate(taijat.get(i).getFireRate());
                            taika.setSize(taijat.get(i).getSize());
                            taika.setFire(taijat.get(i).getFire());
                            taika.setIce(taijat.get(i).getIce());
                            taika.setLightning(taijat.get(i).getLightning());
                            taika.setWind(taijat.get(i).getWind());
                            taika.setLevel(taijat.get(i).getLevel());
                            taika.setType(taijat.get(i).getType());
                            taika.setName(taijat.get(i).getName());
                            taika.setIcon(taijat.get(i).getIcon());
                            taika.setSelected(false);
                            //System.out.println(taika.getName());
                        }
                    }
                    //System.out.println(taika);
                    menuselect = 2;
                }

            } else if (menuselect == 2) {

                for (int i = 0; i < upgradet.size(); i++) {
                    if (koordX > 350 && koordX < 430 && koordY > upgradet.get(i).getType() * 85 + 5 && koordY < upgradet.get(i).getType() * 85 + 85) {

                        if (upgradet.get(i).getPoints() + 1 <= skillpoints && upgradet.get(i).getPoints() < 10 && upgradet.get(i).isUnlocked() == true) {
                            skillpoints -= upgradet.get(i).getPoints() + 1;
                            upgradet.get(i).setPoints(upgradet.get(i).getPoints() + 1);
                        }
                    }
                }

                if (koordX > 450 && koordX < 550 && koordY > 700 && koordY < 800) {
                    for (int i = 0; i < upgradet.size(); i++) {
                        //System.out.println(upgradet.get(i).getName()+" "+upgradet.get(i).getPoints());
                    }

                    for (int i = 0; i < taijat.size(); i++) {
                        if (taijat.get(i).isSelected() == true) {
                            //System.out.println(i);
                            taika.setDamage(taijat.get(i).getDamage() * (1 + ((double) upgradet.get(0).getPoints() / 10)));
                            taika.setFireRate(taijat.get(i).getFireRate() / (int) (1 + ((double) upgradet.get(1).getPoints() / 10)));
                            taika.setSize(taijat.get(i).getSize() * (1 + ((double) upgradet.get(2).getPoints() / 10)));
                            taika.setFire(upgradet.get(3).getPoints());
                            taika.setIce(upgradet.get(4).getPoints());
                            taika.setLightning(upgradet.get(5).getPoints());
                            taika.setWind(upgradet.get(6).getPoints());
                        }
                    }



                    //System.out.println(taika.getSize());
                    //System.out.println(taika.getDamage());
                    //Magic spray = new Magic(20,20,10,1,1,4,0,0,0,0,"spray.png","Magic Spray","Sprays low accuracy magic balls with rapid firerate.\nMagic balls dissapear after while.\nDamage:Medium\nFirerate:High\nSize:Small ");
                    menuselect = 3;
                    spawn = 500;
                }
            } else if (menuselect == 3) {

                for (int i = 0; i < mapit.size(); i++) {
                    if (koordX > mapit.get(i).getX() && koordX < mapit.get(i).getX() + 40 && koordY > mapit.get(i).getY() && koordY < mapit.get(i).getY() + 40) {
                        for (int j = 0; j < mapit.size(); j++) {
                            mapit.get(j).setSelected(false);
                        }
                        mapit.get(i).setSelected(true);
                        selectedMap = i;
                    }
                }

                if (koordX > 330 && koordX < 430 && koordY > 780 && koordY < 840) {
                    menuselect = 2;
                }

                if (koordX > 455 && koordX < 555 && koordY > 780 && koordY < 840) {

                    if (mapit.get(selectedMap).isLocked() || mapit.get(selectedMap).isCleared()) {
                        System.out.println("Ei voida käynnistää");
                    } else {
                        monsters = mapit.get(selectedMap).getMonsters();
                        spawnTimes = mapit.get(selectedMap).getSpawntime();
                        System.out.println("map:" + selectedMap);
                        setBackground(mapit.get(selectedMap).getBackground());
                        vuoro = 0;
                        menu = false;
                    }


                }
                /*g2d.drawRect(330, 780, 100, 60);
                 g2d.drawString("Skill menu", 350, 815);
                 g2d.drawRect(455, 780, 100, 60);
                 g2d.drawString("Start", 490, 815);*/

            }
        }

    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println(apux+" "+apuy);
    }

    public void mouseDragged(MouseEvent e) {
        int koordX = e.getX();
        int koordY = e.getY();

        mx = koordX;
        my = koordY;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            down = true;
        }

        if (code == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            left = true;
        }


        if (code == KeyEvent.VK_W) {
            up = true;
        }
        if (code == KeyEvent.VK_S) {
            down = true;
        }

        if (code == KeyEvent.VK_D) {
            right = true;
        }
        if (code == KeyEvent.VK_A) {
            left = true;
        }

        if (code == KeyEvent.VK_SPACE) {
        }

        if (code == KeyEvent.VK_P) {
            cheat();
        }



    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            down = false;
        }

        if (code == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            left = false;
        }


        if (code == KeyEvent.VK_W) {
            up = false;
        }
        if (code == KeyEvent.VK_S) {
            down = false;
        }

        if (code == KeyEvent.VK_D) {
            right = false;
        }
        if (code == KeyEvent.VK_A) {
            left = false;
        }

        if (code == KeyEvent.VK_1) {
            magic = 1;
        }

        if (code == KeyEvent.VK_2) {
            magic = 2;
        }

        if (code == KeyEvent.VK_3) {
            magic = 3;
        }

        if (code == KeyEvent.VK_4) {
            magic = 4;
        }

        if (code == KeyEvent.VK_5) {
            magic = 5;
        }



    }
}
