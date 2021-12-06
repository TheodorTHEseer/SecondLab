package com.company;

import cretures.pac.Hero;

import java.util.*;
import java.util.stream.Collectors;

import static cretures.pac.Hero.prefix;
//welcome to the XP|-04EB0 ZONE
public class FHW {
    public static void main(String[] args){
        generate();
        letsGo();
    }
    final static private ArrayList <Hero> HwSquad = new ArrayList<>();

    public static void generate() {
        for (int count = 0; count < 15; count++) {
            Hero hwPerson = new Hero(Hero.HNames[count]);
            HwSquad.add(hwPerson);
        }
    }
    public static List <Hero> eList = new ArrayList<>();
    static public void letsGo() {
        HwSquad.forEach(Hero -> System.out.printf("Name: %10s hp: %5d dmg: %5d \n",
                Hero.getName(), Hero.getMaxHp(), Hero.getDefaultDamage()));
            aN();
            bN();
            cN();
            dN();
            eN();
            fN();
            gN();
        }
        private static void aN(){
            System.out.println("a: ");
            System.out.println(FHW.HwSquad.stream()
                    .max(Comparator.comparing(Hero::getMaxHp)).get()
                    .getName());
        }
        private static void bN(){
            System.out.println("b: ");
            System.out.println(FHW.HwSquad.stream()
                    .min(Comparator.comparing(Hero::getMaxHp)).get()
                    .getName());
        }
        private static void cN(){
            System.out.println("c: ");
            FHW.HwSquad.stream()
                    .filter(Hero -> Hero.getDefaultDamage()<100)
                    .sorted(Comparator.comparingInt(Hero::getDefaultDamage))
                    .forEach(Hero-> System.out.printf("Name: %10s dmg: %5d \n",Hero.getName(), + Hero.getDefaultDamage()));
        }
        private static void dN(){
            System.out.println("d : #здесь будет пусто, так как у всех дамаг <100");
            FHW.HwSquad.stream()
                    .filter(Hero -> Hero.getDefaultDamage()>100)
                    .sorted(Comparator.comparingInt(Hero::getDefaultDamage))
                    .forEach(Hero-> System.out.println(Hero.getName()+ " dmg: " + Hero.getDefaultDamage()));
        }
        private static void eN(){
            System.out.println("e: ");
            for (Hero hero : FHW.HwSquad.stream()
                    .filter(Hero -> Hero.getMagicId() == 1)
                    .collect(Collectors.toList())) {
                hero.setHp(hero.getHp() * 2);
                System.out.printf("Name: %10s hp: %5d \n",hero.getName(), hero.getHp());
            }
        }
        private static void fN(){
            System.out.println("f: ");
            boolean leetDetector;
            leetDetector = FHW.HwSquad.stream().noneMatch(Hero -> Hero.getDefaultDamage()==1337);
            if (leetDetector)
                System.out.println("NO leet HERE");
            else{
                eList= new ArrayList<>(FHW.HwSquad);
                for (int count =0; count<eList.size(); count++){
                    if (eList.get(count).getDefaultDamage()==1337){
                        System.out.println(eList.get(count).getName());
                        FHW.HwSquad.get(count).setName(prefix + FHW.HwSquad.get(count).getName());
                    }
                }
            }
        }
        private static void gN(){
            System.out.println("g: ");
            List <Hero> gList= new ArrayList();
            gList = FHW.HwSquad.stream().sorted(Comparator.comparingInt(Hero::getMagicId)).collect(Collectors.toList());
            for (Hero hero : gList) {
                System.out.format("Name: %8s использует %6s \n", hero.getName(), hero.getMagicName());
            }
        }
    }

