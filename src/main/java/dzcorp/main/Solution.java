package dzcorp.main;

import dzcorp.entity.Photo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) throws Exception {

         /*
        Expected output :
        Warsaw02.jpg
        London1.png
        Warsaw01.png
        Paris2.jpg
        Paris1.jpg
        London2.jpg
        Paris3.png
        Warsaw03.jpg
        Warsaw09.png
        Warsaw07.jpg
        Warsaw06.jpg
        Warsaw08.jpg
        Warsaw04.jpg
        Warsaw05.jpg
        Warsaw10.jpg
        */

        String photo = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n"
                + "john.png, London, 2015-06-20 15:13:22\n"
                + "myFriends.png, Warsaw, 2013-09-05 14:07:13\n"
                + "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n"
                + "pisatower.jpg, Paris, 2015-07-22 23:59:59\n"
                + "BOB.jpg, London, 2015-08-05 00:02:03\n"
                + "notredame.png, Paris, 2015-09-01 12:00:00\n"
                + "me.jpg, Warsaw, 2013-09-06 15:40:22\n"
                + "a.png, Warsaw, 2016-02-13 13:33:50\n"
                + "b.jpg, Warsaw, 2016-01-02 15:12:22\n"
                + "c.jpg, Warsaw, 2016-01-02 14:34:30\n"
                + "d.jpg, Warsaw, 2016-01-02 15:15:01\n"
                + "e.jpg, Warsaw, 2016-01-02 09:49:09\n"
                + "f.jpg, Warsaw, 2016-01-02 10:55:32\n"
                + "g.jpg, Warsaw, 2016-02-29 22:13:11";


        System.out.println(solution(photo));
    }


    private static String solution(String photo) throws ParseException {
        String[] photos = photo.split("\\R");

        List<Photo> jhonPhotos = new ArrayList<>();

        int sequence = 1;
        for (String s : photos) {
            Photo jhonPhoto = new Photo();
            String[] dataPhoto = s.split(",");
            Instant dateTime = formatDate(dataPhoto[2]);

            jhonPhoto.setPhoto(dataPhoto[0]);
            jhonPhoto.setLocation(dataPhoto[1]);
            jhonPhoto.setTime(dateTime);
            jhonPhoto.setSequence(sequence++);

            jhonPhotos.add(jhonPhoto);
        }

        Map<String, List<Photo>> collect = jhonPhotos.stream()
                .sorted(Comparator.comparing(Photo::getTime))
                .collect(Collectors.groupingBy(Photo::getLocation));

        StringBuilder builder = new StringBuilder();

        Map<String, Integer> data = new HashMap<>();

        for (Map.Entry<String, List<Photo>> entry : collect.entrySet()) {
            List<Photo> cityPhotos = entry.getValue();
            int counter = 1;

            for (Photo jhonPhoto : cityPhotos) {
                String[] extension = jhonPhoto.getPhoto().split("\\.");
                if (String.valueOf(entry.getValue().size()).length() >= 2) {
                    if (String.valueOf(counter).length() == 2) {
                        builder.append(jhonPhoto.getLocation())
                                .append(counter++).append(".")
                                .append(extension[1]).append(",")
                                .append(jhonPhoto.getSequence()).append("\n");
                    } else {
                        builder.append(jhonPhoto.getLocation()).append("0")
                                .append(counter++).append(".")
                                .append(extension[1]).append(",")
                                .append(jhonPhoto.getSequence()).append("\n");
                    }
                } else {
                    builder.append(jhonPhoto.getLocation())
                            .append(counter++).append(".")
                            .append(extension[1])
                            .append(",").append(jhonPhoto.getSequence()).append("\n");
                }
            }
        }

        getPhotoNameAndSequence(builder, data);
        List<String> sortedPhotos = constructAndSort(data);
        return constructFinalPhotoNames(sortedPhotos);
    }

    private static String constructFinalPhotoNames(List<String> collect1) {
        StringBuilder resultBuilder = new StringBuilder();
        for (String value : collect1) {
            resultBuilder.append(value).append("\n");
        }
        return resultBuilder.toString();
    }

    private static void getPhotoNameAndSequence(StringBuilder builder, Map<String, Integer> data) {
        String[] photoNames = builder.toString().split("\\R");
        for (String photoName : photoNames) {
            String[] split = photoName.split(",");
            data.put(split[0], Integer.parseInt(split[1]));
        }
    }

    private static List<String> constructAndSort(Map<String, Integer> data) {
        return data.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static Instant formatDate(String source) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = formatter.parse(source);
        return date.toInstant();
    }


}
