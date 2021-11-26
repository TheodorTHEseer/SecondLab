package com.company;

import cretures.pac.Hero;

import javax.crypto.AEADBadTagException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cretures.pac.Hero.prefix;

public class FHW {
    static private ArrayList <Hero> HwSquad = new ArrayList<>();

    public static void generate() {
        for (int count = 0; count < 15; count++) {
            Hero hwPerson = new Hero(Hero.HNames[count]);
            HwSquad.add(hwPerson);
        }
    }
    public static List <Hero> eList = new ArrayList<>();
    static public void letsGo() {
        HwSquad.forEach(Hero -> System.out.println(Hero.getName() + " hp: " + Hero.getMaxHp() +
                " dmg " + Hero.getDefaultDamage()));
            aN(HwSquad);
            bN(HwSquad);
            cN(HwSquad);
            dN(HwSquad);
            fN(HwSquad);
            gN(HwSquad);

        }
        private static void aN(ArrayList <Hero> HwSquad){
            System.out.println("a: ");
            System.out.println(HwSquad.stream()
                    .max(Comparator.comparing(Hero::getMaxHp)).get()
                    .getName());
        }
        private static void bN(ArrayList<Hero> HwSquad){
            System.out.println("b: ");
            System.out.println(HwSquad.stream()
                    .min(Comparator.comparing(Hero::getMaxHp)).get()
                    .getName());
        }
        private static void cN (ArrayList <Hero> HwSquad){
            System.out.println("c: ");
            HwSquad.stream()
                    .filter(Hero -> Hero.getDefaultDamage()<100)
                    .sorted((o1, o2) -> o1.getDefaultDamage() - o2.getDefaultDamage())
                    .forEach(Hero-> System.out.println(Hero.getName()+ " dmg: " + Hero.getDefaultDamage()));
        }
        private static void dN (ArrayList <Hero> HwSquad){
            System.out.println("d : #здесь будет пусто, так как у всех дамаг <100");
            HwSquad.stream()
                    .filter(Hero -> Hero.getDefaultDamage()>100)
                    .sorted((o1, o2) -> o1.getDefaultDamage() - o2.getDefaultDamage())
                    .forEach(Hero-> System.out.println(Hero.getName()+ " dmg: " + Hero.getDefaultDamage()));
        }
        private static void eN (ArrayList <Hero> HwSquad){
            System.out.println("e: ");
            for (Hero hero : HwSquad.stream()
                    .filter(Hero -> Hero.getMagicId() == 1)
                    .collect(Collectors.toList())) {
                hero.setHp(hero.getHp() * 2);
                System.out.println(hero.getName() + " hp: " + hero.getHp());
            }
        }
        private static void fN(ArrayList <Hero> HwSquad){
            System.out.println("f: ");
            boolean leetDetector;
            leetDetector = HwSquad.stream().noneMatch(Hero -> Hero.getDefaultDamage()==1337);
            if (leetDetector == true)
                System.out.println("NO leet HERE");
            else{
                eList=HwSquad.stream().collect(Collectors.toList());
                for (int count =0; count<eList.size(); count++){
                    if (eList.get(count).getDefaultDamage()==1337){
                        System.out.println(eList.get(count).getName());
                        HwSquad.get(count).setName(prefix + HwSquad.get(count).getName());
                    }
                }
            }
        }
        private static void gN (ArrayList <Hero> HwSquad){
            System.out.println("g: ");
            List <Hero> gList= new ArrayList();
            gList = HwSquad.stream().sorted((o1, o2) -> o1.getMagicId() - o2.getMagicId()).collect(Collectors.toList());
            for (int count =0; count < gList.size(); count++){
                System.out.println(gList.get(count).getName() + "Владеет: " + gList.get(count).getMagicName());
            }
        }

    }

