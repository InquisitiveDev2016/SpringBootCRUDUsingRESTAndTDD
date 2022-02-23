package com.example.demo.controller;

import com.example.demo.announcements.AnnounceUserHasReachedEndpoint;
import com.example.demo.user.User;
import com.example.demo.user.SetUserFields;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/*

add a couple of endpoints
modify your existing endpoint
and I want users that post you should save them in a file (such as a txt file)
 add extra parameters for the users such as age,job, id
 add an endpoint that will list all the users that are stored in the file

 add an endpoint where you can update an existing user. If I post a user then you can edit the user fields such as age,job,etc.

 -any age can open a bank account
  - 15+ can trade stocks
  - instead of age use dob
  - add an address field for the user

  and if a user resides in a certain area in a certain city we need to present a preconfigured promotion message to display for them



 */


@RestController
@RequestMapping("/")
public class NameController {
    private AnnounceUserHasReachedEndpoint endPointAnnouncementService;

    private final String usersTextFilePath = "C:\\DEV\\demo\\users.txt";

    private final int currentYear = 2022;

    public NameController(AnnounceUserHasReachedEndpoint endPointAnnouncementService) {
         this.endPointAnnouncementService = endPointAnnouncementService;
    }

    @PostMapping(value="/{name}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public User post(@RequestBody User user, @PathVariable(name = "name") String name) {
        return user;
    }


    @PostMapping(value="/{name}/{userId}/{userDateOfBirth}/{userJob}/{userAddress}/{canOpenBankAccount}/{canTradeStocks}/{userPromotionalMessage}",
            consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUserToTextFile(@RequestBody User user,
                                  @PathVariable(name="userNameToAdd") String userNameToAdd,
                                  @PathVariable(value="userId") int userId,
                                  @PathVariable(value="userDateOfBirth") String userDateOfBirth,
                                  @PathVariable(value="userJob") String userJob,
                                  @PathVariable(value="userAddress") String userAddress,
                                  @PathVariable(value="canOpenBankAccount") boolean canOpenBankAccount,
                                  @PathVariable(value="canTradeStocks") boolean canTradeStocks,
                                  @PathVariable(value="userPromotionalMessage") String userPromotionalMessage) throws Exception
     {

        new SetUserFields(user, userId,userDateOfBirth, userJob, userAddress,canOpenBankAccount, canTradeStocks, userPromotionalMessage).setTheFields();
        addUserToTextFile(user);

        return user;

    }




    @PostMapping(value="/{name}/{userId}/{userDateOfBirth}/{userJob}/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user, @PathVariable(name="userNameToAdd") String userNameToAdd,
                           @PathVariable(value="userId") int userId,
                           @PathVariable(value="userDateOfBirth") String userDateOfBirth,
                           @PathVariable(value="userJob") String userJob,
                           @PathVariable(value="userAddress") String userAddress,
                           @PathVariable(value="canOpenBankAccount") boolean canOpenBankAccount,
                           @PathVariable(value="canTradeStocks") boolean canTradeStocks,
                           @PathVariable(value="userPromotionalMessage") String userPromotionalMessage) throws Exception
    {

        new SetUserFields(user, userId, userDateOfBirth, userJob, userAddress, canOpenBankAccount, canTradeStocks, userPromotionalMessage).setTheFields();


        if(searchForUserInTheUsersTextFile(user)) {

            updateTheUserFieldsInTheTextFile(user);

        }

       else throw new IllegalArgumentException("User not found in the text file");

        return user;

    }






    @GetMapping(value="/getList", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public String listAllUsers() throws Exception {

        ArrayList<String> listOfUserDetailsInTheTextFile = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(usersTextFilePath));

        for(String line; (line = br.readLine()) != null; ) {
            listOfUserDetailsInTheTextFile.add(line);
        }

        System.out.println(Arrays.toString(listOfUserDetailsInTheTextFile.toArray()));

        return Arrays.toString(listOfUserDetailsInTheTextFile.toArray());
    }


    private void addUserToTextFile(User user) throws Exception {

        FileWriter fileWriter = new FileWriter(usersTextFilePath, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        String userDateOfBirth = user.getUserDateOfBirth();
        String[] userDateOfBirthSplit = user.getUserDateOfBirth().split("/");

        int userYearOfBirth = Integer.parseInt(userDateOfBirthSplit[2]);
        int userAge = currentYear - userYearOfBirth;

        printWriter.println("Name: " + user.getName());
        printWriter.println("id: " + user.getUserId());
        printWriter.println("date of birth: " + user.getUserDateOfBirth());
        printWriter.println("job: " + user.getUserJob());
        printWriter.println("address: " + user.getUserAddress());
        printWriter.println("can open bank account: " + user.isCanOpenBankAccount());

        if(userAge >= 15) {
            printWriter.println("can trade stocks: " + true);
        } else  {
            printWriter.println("can trade stocks: " + false);
        }


        if(user.getUserAddress().contains("15 Queen St E,Brampton,ON")) {
            printWriter.println("promo message: " + "15% lower transaction fees for stocks");
        } else if(user.getUserAddress().contains("25 King St W,Toronto,ON")) {
            printWriter.println("promo message: " + "$1000 cash sign up bonus");
        } else {
            printWriter.println("promo message: " + user.getUserPromotionalMessage());
        }

        printWriter.println();

        printWriter.close();
        fileWriter.close();
    }

    private boolean searchForUserInTheUsersTextFile(User user) throws Exception {

        System.out.println("List1: " + Arrays.toString(addTextFileLinesToList().toArray()));

        for(int i = 0; i < addTextFileLinesToList().size(); i++) {
            if(addTextFileLinesToList().get(i).equals("Name: " + user.getName())) return true;
        }

        return false;
    }

    private void updateTheUserFieldsInTheTextFile(User user) throws Exception {

        ArrayList<String> list = addTextFileLinesToList();

        updateTextFileArrayList(list, user);

        System.out.println("New List: " + Arrays.toString(list.toArray()));

        FileWriter fileWriter = new FileWriter(usersTextFilePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for(int i = 0; i < list.size(); i++) {

            printWriter.println(list.get(i));

        }

        printWriter.close();
        fileWriter.close();

    }

    private ArrayList<String> addTextFileLinesToList() throws Exception {

        ArrayList<String> listOfLines = new ArrayList<>();
        BufferedReader bufReader = new BufferedReader(new FileReader(usersTextFilePath));

        String line = bufReader.readLine();

        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }

        bufReader.close();

        return listOfLines;

    }

    private void updateTextFileArrayList(ArrayList<String> list, User user) {

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equals("Name: " + user.getName())) {

                int j = i+1;
                while(j < list.size() && !(list.get(j).contains("Name: "))) {

                    String userDateOfBirth = user.getUserDateOfBirth();
                    String[] userDateOfBirthSplit = user.getUserDateOfBirth().split("/");

                    int userYearOfBirth = Integer.parseInt(userDateOfBirthSplit[2]);
                    int userAge = currentYear - userYearOfBirth;

                    if(list.get(j).contains("id")) list.set(j, "id: " + user.getUserId());
                    if(list.get(j).contains("date of birth")) list.set(j, "date of birth: " + user.getUserDateOfBirth());
                    if(list.get(j).contains("job")) list.set(j, "job: " + user.getUserJob());
                    if(list.get(j).contains("address")) list.set(j, "address: " + user.getUserAddress());
                    if(list.get(j).contains("can open bank account")) list.set(j, "can open bank account: " + user.isCanOpenBankAccount());

                    if(list.get(j).contains("can trade stocks")) {

                        if(userAge >= 15) {
                            list.set(j, "can trade stocks: " + true);
                        } else  {
                            list.set(j, "can trade stocks: " + false);
                        }

                    }

                    if(list.get(j).contains("promo message")) {

                        if(user.getUserAddress().contains("15 Queen St E,Brampton,ON")) {
                            list.set(j, "promo message: " + "15% lower transaction fees for stocks");
                        } else if(user.getUserAddress().contains("25 King St W,Toronto,ON")) {
                            list.set(j, "promo message: " + "$1000 cash sign up bonus");
                        } else {
                            list.set(j, "promo message: " + user.getUserPromotionalMessage());
                        }


                    }

                    j++;

                }
            }
        }

    }




}
