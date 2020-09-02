package com.nicolaslopez82.challenges.networkoffriends;

import java.util.*;

/**
 * The goal for this challenger is to implement 2 methods, "addFriend" and getFriendsOfTiers.
 * Based in how is the data, I assumed for the first impression that it could be resolved through
 * graph, DFS or BFS for instance.
 *
 * However, in order to get a quick implementation and then go deeper into a better solution; I created
 * this solution for storing, implementing and showing the data.
 *
 * I consider that with a DFS it could be a better solution in terms of storing and performance.
 *
 * Challenger:
 *
 * Given the load of data that is between lines 24 and 30, implement 2 method in order to store the data
 * and show the data in the return of method getFriendsOfTiers.
 * When we looking for friend "A", it method should return a Map<Integer, List<List<String>> as {(1,[B,C])}
 */
public class Main {

    public static void main(String[] args) {
        NetworkOfFriends networkOfFriends = new NetworkOfFriends();

        networkOfFriends.addFriend("A", "B");
        networkOfFriends.addFriend("A", "C");
        networkOfFriends.addFriend("B", "C");
        networkOfFriends.addFriend("B", "D");
        networkOfFriends.addFriend("C", "E");
        networkOfFriends.addFriend("F", "H");
        networkOfFriends.showData();
    }

    public static class NetworkOfFriends{

        /* How the data is going to be stored.
        _________________
        |_A_|_B_|_C_|_F_| -> tiers
        | B | C | E | H | -> data
        | C | D |   |   | -> data

         */

        private static Map<Integer, List<String>> data = new HashMap<>();
        private static Map<Integer, String> tiers = new HashMap<>();
        private static Integer count = 1;

        /**
         * Method that add a new Tier indicated by friend1 and using the count as a key.
         * Then, add the count as a key, and the new List<String> of friends (friend2).
         * Increment the count in 1.
         * Otherwise if the tiers doesn't exist, is looking for the friend's tier and
         * return the key where the friend is located in the map of tiers.
         * After that, it's going to add the new friend to the tier's friend.
         * @param friend1
         * @param friend2
         */
        public static void addFriend(String friend1, String friend2){

            if(!(tiers.containsValue(friend1))){
                tiers.put(count, friend1);
                data.putIfAbsent(count, new LinkedList<>(Arrays.asList(friend2)));
                count++;
            }else {
                Integer key = findKeyOnTiers(friend1);
                data.get(key).add(friend2);
                data.put(key, data.get(key));
            }
        }

        /**
         * Find for the key where the friend is located into the static Map of tiers.
         * @param friendName
         * @return the key where the friend is located into the static Map tiers.
         */
        private static Integer findKeyOnTiers(String friendName){
            for (Map.Entry<Integer, String> map : tiers.entrySet()){
                if(map.getValue().equals(friendName)){
                    return map.getKey();
                }
            }
            return -1;
        }

        /**
         * Method that show data.
         */
        public static void showData(){
            //System.out.println(data.toString() + '\n');

            System.out.println("Result for getFriendTiers(\"A\") : ");
            System.out.println(getFriendOfTier("A"));
        }

        /**
         *
         * Method that receive a friend's name in order to be found into the Map of tiers,
         * and returns all friends associated that are stored into static Map data.
         * For example: getFriendTiers("A"), should be returned: {(1,[B,C]))}
         * @param name
         * @return
         */
        public static Map<Integer, List<String>> getFriendOfTier(String name){
            Map<Integer, List<String>> map = new HashMap<>();
            Integer tier = findKeyOnTiers(name);
            if(tier != null){
                map.put(tier, data.get(tier));
            }
            return map;
        }
    }
}

