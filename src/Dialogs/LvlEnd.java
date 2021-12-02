package Dialogs;

import java.util.HashMap;
import java.util.Map;

public class LvlEnd {
  private static Map<Integer, String> lvlEndDialogs = new HashMap<>();
  private static Map<Integer, String> lvlEndAfterAnsY = new HashMap<>();
  private static Map<Integer, String> lvlEndAfterAnsN = new HashMap<>();
  private static Map<Integer, String> lvlEndTAns = new HashMap<>();

  private static void loadEndDialogs() {
    lvlEndDialogs.put(0, "\u001B[36m Орк:\u001B[0m Лок-тар огар, береги свою шею, гном! \n #взмахивает топором#  \n я ценю храбрецов, поэтому перед смертью ответь на вопрос мудрецов моего клана: \n Летит быстрее копья, ни свиста, ни визга, убить не может, не пробьют дрянной брони, но если видишь в глазах у тёмного эльфа - беги, чтобы не догнал.");
    lvlEndDialogs.put(1, "\u001B[36m Покачивающийся труп:\u001B[0m Не надейся на быструю смерть! Ты победил жалких рабов, но битвы со мной не достоен. Хотя если ответишь на вечный вопрос, может я и дам тебе пройти, но победить меня не надейся: \n что можно отнять и у бедного, и у богатого, но вернуть нельзя никому?");
    lvlEndDialogs.put(2, "\u001B[36m Орочий шаман:\u001B[0m У нас нет времени воевать, мы должны сыграть!!! \n Мечтают ли орки об электросвиняьх?");
    lvlEndDialogs.put(3, "\u001B[36m Тролль в старом доспехе:\u001B[0m Путник, я поспорил со своим братом , подскажи мне: \n что ваши женщины носят на головах, а мужчины у сердца?");
    lvlEndDialogs.put(4, "\u001B[36m Тролль в новом доспехе:\u001B[0m Кинтуп, ты видно уже встретил моего братца, надеюсь ты его убил, но, что ваши мужчины носят у сердца, а женщины на головах?");
    lvlEndDialogs.put(5, "\u001B[36m Наёмник:\u001B[0m Сколько ты мне заплатишь, чтобы я не убил тебя прямо здесь?");
    lvlEndDialogs.put(6, "\u001B[36m Тролль знахарь:\u001B[0m Человеческий организм состоит на 80% из жидкости. У некоторых - из тормозной. \nА что залито в тебя, человечешка?");
    lvlEndDialogs.put(7, "\u001B[36m Страж дверей:\u001B[0m Если смертные достают, то достают до смерти. Ты победил 12 моих прислужников, но они были глупы, я иной. А какой ты?");
    lvlEndDialogs.put(8, "\u001B[36m Прислужник:\u001B[0m Я вижу Великую Тьму!! Тьфу, опять капюшон сполз на глаза. А какова чёрта ты здесь и моих братьев не видно? Ах ты, гад, ты же их порубил. Ладно, я никогда не был самым сильным, но ключа от сундука с золотом тебе не видать! \n Хотя если заплатишь мне немного денег, то сможем договориться.");
    lvlEndDialogs.put(9, "\u001B[36m Сундук:\u001B[0m Не не не. Что ты хочешь найти внутри меня? Ты вообще в своём уме?");

    lvlEndTAns.put(0, "свет");
    lvlEndTAns.put(1, "жизнь");
    lvlEndTAns.put(2, "да");
    lvlEndTAns.put(3, "платки");
    lvlEndTAns.put(4, "сталь");
    lvlEndTAns.put(5, "666");
    lvlEndTAns.put(6, "вода");
    lvlEndTAns.put(7, "13");
    lvlEndTAns.put(8, "100");
    lvlEndTAns.put(9, "лекарство");

    lvlEndAfterAnsY.put(0, "Ну и черт с тобой, шагай дальше, ");
    lvlEndAfterAnsY.put(1, "Может быть я и не хочу с тобой драться, я всё равно труп, иди своей дорогой, ");
    lvlEndAfterAnsY.put(2, "Заходи ещё, ");
    lvlEndAfterAnsY.put(3, "Скажи этому братцу-придурку, что сказал мне, ");
    lvlEndAfterAnsY.put(4, "Ну и молодчина, хороший ответ, ");
    lvlEndAfterAnsY.put(5, "Щедро, господин ");
    lvlEndAfterAnsY.put(6, "Эхэхххэ, ну да, правильно, ");
    lvlEndAfterAnsY.put(7, "Иди, смертный дальше, ");
    lvlEndAfterAnsY.put(8, "Хорошо иметь с тобой дело, ");
    lvlEndAfterAnsY.put(9, "Ну и доброго здоровьечка, ");

    lvlEndAfterAnsN.put(0, "Умри, гном!");
    lvlEndAfterAnsN.put(1, "Я покажу тебе смерть!");
    lvlEndAfterAnsN.put(2, "Ну и дурак, выметайся!");
    lvlEndAfterAnsN.put(3, "Греби к моему братцу, придурок с таким ответом!");
    lvlEndAfterAnsN.put(4, "Да что за сборище идиотов!");
    lvlEndAfterAnsN.put(5, "Малова-то будет!");
    lvlEndAfterAnsN.put(6, "Ну тормоз же ты!");
    lvlEndAfterAnsN.put(7, "Ты просто идиот!");
    lvlEndAfterAnsN.put(8, "Ты издеваешься?!");
    lvlEndAfterAnsN.put(9, "Ахахаха! Ещё чего?!");
  }

  public static void loadDialogs() {
    loadEndDialogs();
  }

  public static String getEndDialog(int key) {
    return lvlEndDialogs.get(key);
  }

  public static String getEndAfterY(int key) {
    return lvlEndAfterAnsY.get(key);
  }

  public static String getEndAfterN(int key) {
    return lvlEndAfterAnsN.get(key);
  }

  public static String getEndTAns(int key) {
    return lvlEndTAns.get(key);
  }
}
