package example.com.learningtopics;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import example.com.learningtopics.Adapters.TopicRecyclerViewAdapter;
import example.com.learningtopics.Utils.SharedPrefUtils;


/**
 * Created by christiealtadonna on 4/7/17.
 */

public class Connect extends AsyncTask<String,Void,String> {

  //  TextView user_name_connect, location;
    private Context context;
 //   private String full_name;

    private String username_class;
    private String password_class;

    private String topic_ID;
    private String post_ID;
    //Take these in constructor to display on main page
//    public Connect(Context context, TextView user_name, String username, String password) {
//        this.context = context;
//        this.user_name_connect = user_name;
//        this.username_class = username;
//        this.password_class = password;
//
//    }

    //Connect constructor for registration
    public Connect(Context context, String username_class, String password_class) {
        this.context = context;
        this.username_class = username_class;
        this.password_class = password_class;

    }

    //Connect constructor for configuration
    public Connect(Context context, String username_class) {
        this.context = context;
        this.username_class = username_class;
    }

//    //Connect constructor for get_location
//    public Connect(Context context, TextView location) {
//        this.context = context;
//        this.location = location;
//    }

    //Constructor for MainActivity
    public Connect(Context context) {
        this.context = context;
    }

    public Connect(){

    }

    protected void onPreExecute() {
    }


    @Override
    protected String doInBackground(String... params) {

        Log.d("entered doInBackground", "eneter");
        //POST method
        try {
            String method = (String) params[0];

            //String method = "login";
            String username = (String) params[1];
            Log.d("username in Connect :", username);

            String data = "";
            if (method.equals("login")) {
                String password = (String) params[2];
                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
                data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            } else if (method.equals("register")) {
                String password = (String) params[2];
                String fullname = (String) params[3];
                String college = (String) params[4];
                String grade = (String) params[5];
                String major =(String) params[6];
                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
                data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                data += "&" + URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(fullname, "UTF-8");
                data += "&" + URLEncoder.encode("school", "UTF-8") + "=" + URLEncoder.encode(college, "UTF-8");
                data += "&" + URLEncoder.encode("grade", "UTF-8") + "=" + URLEncoder.encode(grade, "UTF-8");
                data += "&" + URLEncoder.encode("major", "UTF-8") + "=" + URLEncoder.encode(major, "UTF-8");



            }
            else if(method.equals("topic_page")){
                String topicID = (String) params[1];
                topic_ID = topicID;
                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
                data += "&" + URLEncoder.encode("topicId", "UTF-8") + "=" + URLEncoder.encode(topicID, "UTF-8");
            }
            else if(method.equals("like")){
                String post_id = (String) params[1];
                String likes = (String) params[2];
                String topicID = (String) params[3]; //so you can get topics back
                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
                data += "&" + URLEncoder.encode("post_id", "UTF-8") + "=" + URLEncoder.encode(post_id, "UTF-8");
                data += "&" + URLEncoder.encode("likes", "UTF-8") + "=" + URLEncoder.encode(likes, "UTF-8");
                data += "&" + URLEncoder.encode("topic_id", "UTF-8") + "=" + URLEncoder.encode(topicID, "UTF-8");

            }
            else if(method.equals("add_post")){

                String topic_id = (String) params[1];
//                Log.d("inside connect topic ",topic_id);

                String body = (String) params[2];
                String usrname = (String) params[3];
                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
                data += "&" + URLEncoder.encode("topic_id", "UTF-8") + "=" + URLEncoder.encode(topic_id, "UTF-8");
                data += "&" + URLEncoder.encode("body", "UTF-8") + "=" + URLEncoder.encode(body, "UTF-8");
                data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(usrname, "UTF-8");


            }
            else if(method.equals("comments")){
                String post_id = (String) params[1];
                post_ID = post_id;
                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
                data += "&" + URLEncoder.encode("post_id", "UTF-8") + "=" + URLEncoder.encode(post_id, "UTF-8");
            }
            else if(method.equals("add_comment")){
                String usrname = (String) params[1];
                String body = (String) params[2];
                String post_id = (String) params[3];

                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
                data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(usrname, "UTF-8");
                data += "&" + URLEncoder.encode("body", "UTF-8") + "=" + URLEncoder.encode(body, "UTF-8");
                data += "&" + URLEncoder.encode("post_id", "UTF-8") + "=" + URLEncoder.encode(post_id, "UTF-8");
            }
            else if(method.equals("profile")){
                String user_name= (String) params[1];
                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
                data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8");
            }
            else if(method.equals("add_topic")){
                String topic= (String) params[1];
                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
                data += "&" + URLEncoder.encode("topic", "UTF-8") + "=" + URLEncoder.encode(topic, "UTF-8");
            }

//            else if(method.equals("topicsList")){
//                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
//            }
//            else if (method.equals("configure")) {
//                String fullname = (String) params[2];
//                String interests = (String) params[3];
//                String update_frequency = (String) params[4];
//                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
//                data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
//                // data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
//                data += "&" + URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(fullname, "UTF-8");
//                data += "&" + URLEncoder.encode("interests", "UTF-8") + "=" + URLEncoder.encode(interests, "UTF-8");
//                data += "&" + URLEncoder.encode("update_frequency", "UTF-8") + "=" + URLEncoder.encode(update_frequency, "UTF-8");
//
//
//            } else if (method.equals("get_mac")) {
//                String device_mac = params[2];
//                String connected_mac = params[3];
//                //String around_wifi_list = params[4];
//                Log.d("device mac", device_mac);
//                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
//                data += "&" + URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
//                data += "&" + URLEncoder.encode("device_mac", "UTF-8") + "=" + URLEncoder.encode(device_mac, "UTF-8");
//                data += "&" + URLEncoder.encode("connected_mac", "UTF-8") + "=" + URLEncoder.encode(connected_mac, "UTF-8");
//                //data += "&" + URLEncoder.encode("around_wifi_list", "UTF-8") + "=" + URLEncoder.encode(around_wifi_list, "UTF-8");
//            } else if (method.equals("get_location")) {
//                String fullname = params[1];
//                full_name = fullname;
//                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
//                data += "&" + URLEncoder.encode("fullname", "UTF-8") + "=" + URLEncoder.encode(fullname, "UTF-8");
//            } else if (method.equals("get_news")) {
//                String interest = params[1];
//                data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(method, "UTF-8");
//                data += "&" + URLEncoder.encode("interest", "UTF-8") + "=" + URLEncoder.encode(interest, "UTF-8");
//            }

            //fill in your_computer_id ******
            Log.d("Before url ", "link");
            //Make sure you use your IP address bc localhost doesnt work for emulator
            String link = "http://128.180.143.18:8888/demo/finalproject.php";
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            Log.d("After trying to open","connection");
            conn.setDoOutput(true);
            Log.d("Exc conn.setDoOutput", data);
            //write this data to the link
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());  //WHERE ERROR OCCURS
            Log.d("Exc outstrm", data);
            wr.write(data);
            wr.flush();
            Log.d("Excflush:", data);
            //Open stream to receice the responded message
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Log.d("exc buffread:", data);
            StringBuilder sb = new StringBuilder();
            String line = null;
            Log.d("Exc after strg builder", "sting");
            //Read Server Response
            while ((line = reader.readLine()) != null) {
                Log.d("enter while ", "enter");
                sb.append(line);
            }
            Log.d("My Result:", sb.toString());

            return sb.toString();


        } catch (Exception e) {
            Log.d("Enter exce in connect", "exception");
            Log.d("Exception: ", e.getMessage());
            return new String("Exception: " + e.getMessage());
        }
    }




    protected void onPostExecute(String result){
        String [] r = result.split(";");
//        if(r[0].startsWith("Login")){
//            this.context.startActivity(new Intent(context, HomeActivity.class).putExtra("username",r[1]).putExtra("fullname",r[2]).putExtra("college",r[3]).putExtra("grade",r[4]).putExtra("major",r[5]).putExtra("topics", r[6]));
//            //set global username
//            SharedPrefUtils.setUsernamePreference(context, r[1]);
//
//
//        }
        if(r[0].startsWith("Login")){
            Log.d("in login ", "d");
            this.context.startActivity(new Intent(context, HomeActivity.class).putExtra("username",r[1]).putExtra("fullname",r[2]).putExtra("college",r[3]).putExtra("grade",r[4]).putExtra("major",r[5]).putExtra("topics", r[6]));
            //set global username
            SharedPrefUtils.setUsernamePreference(context, r[1]);


        }
//        else if(r[0].startsWith("Topic List")){
//            //Call a function in HomeActivity
//            HomeActivity homeActivity = (HomeActivity)context;
//            homeActivity.populateRecyclerView(r[6]);
//
//        }
        else if(result.equals("Success Registration")){
            this.context.startActivity(new Intent(context, LoginActivity.class));

        }
        else if(result.equals("Failed Login")){
            Toast.makeText(context, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        }
        else if(r[0].startsWith("Topics")){

            this.context.startActivity(new Intent(context, HomeActivity.class).putExtra("topics",r[1]));
        }
        else if(r[0].startsWith("Posts")){
            //CONTEXT NEVER GETS ASSIGNED
            this.context.startActivity(new Intent(context, TopicDetailsActivity.class).putExtra("posts",r[1]).putExtra("topicID",topic_ID));

        }
        else if(result.equals("Failed selecting posts")){
            this.context.startActivity(new Intent(context, TopicDetailsActivity.class).putExtra("posts","none").putExtra("topicID", topic_ID));
        }
        else if(result.startsWith("Like")){
           TopicDetailsActivity topicDetails= (TopicDetailsActivity) context;
            topicDetails.refreshPage(r[1]);
        }
        else if(result.startsWith("Add Post")){
            TopicDetailsActivity topicDetails= (TopicDetailsActivity) context;
            topicDetails.refreshPage(r[1]);
        }
        else if(r[0].startsWith("Comments")){
            this.context.startActivity(new Intent(context, CommentsActivity.class).putExtra("comments",r[1]));

        }
        else if(r[0].startsWith("Profile")){
            this.context.startActivity(new Intent(context, ProfileActivity.class).putExtra("fullname",r[1]).putExtra("school",r[2]).putExtra("major",r[3]).putExtra("grade",r[4]));

        }
        else if(result.startsWith("Success Add Comment")){
            CommentsActivity commentsActivity = (CommentsActivity) context;
            commentsActivity.refreshComments(r[1]);
        }
        else if(result.startsWith("Failed to load comments")){
            this.context.startActivity(new Intent(context, CommentsActivity.class).putExtra("comments","no comments").putExtra("post_id", post_ID));

        }
        else if(result.startsWith("Success Add Topic")){
            HomeActivity homeActivity = (HomeActivity) context;
            homeActivity.refreshTopics(r[1]);
        }
        //Update list of posts
    }

}