package jpb29.cs262.com.homework2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;

//    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
//            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv = (ListView) findViewById(R.id.listofJsonObjects);
        Thread t = new Thread(new Runnable() {
            public void run() {
                HttpAsyncTask myTask = new HttpAsyncTask();
                myTask.execute("https://calvincs262-monopoly.appspot.com/monopoly/v1/players");
                Log.e("SIZE", String.valueOf(myTask.items.size()));
            }
        });
        t.start();


    }
    public void fetchBtn(View view) {
        EditText search = findViewById(R.id.filterField);
        String searchstring = search.getText().toString();

        if (searchstring.equals(""))
        {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    HttpAsyncTask myTask = new HttpAsyncTask();
                    myTask.execute("https://calvincs262-monopoly.appspot.com/monopoly/v1/players");
                    Log.e("SIZE", String.valueOf(myTask.items.size()));
                }
            });
            t.start();
        } else {
            lv.setAdapter(null);
            final String searchstringnew = searchstring;
            Thread t = new Thread(new Runnable() {
                public void run() {

                    HttpAsyncTask myTask = new HttpAsyncTask();
                    myTask.execute("https://calvincs262-monopoly.appspot.com/monopoly/v1/player/" + searchstringnew);
                    Log.e("SIZE", String.valueOf(myTask.items.size()));
                }
            });
            t.start();
        }

    }

    class TestAdapter extends BaseAdapter {

        ArrayList<listItem> listItem;


        Context mContext;
        //constructor
        public TestAdapter(Context mContext, ArrayList<listItem> listItem) {
            this.mContext = mContext;
            this.listItem = listItem;

        }

        public int getCount() {
            return listItem.size();
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View arg1, ViewGroup viewGroup)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.list_item1, viewGroup, false);


            TextView tv = (TextView)row.findViewById(R.id.tv);
            Log.e("id is: ", String.valueOf(listItem.get(position).id));
            Log.e("name is: ", String.valueOf(listItem.get(position).name));
            Log.e("email is: ", String.valueOf(listItem.get(position).email));

            if (tv == null)
            {
                Log.e("TextView","iSNULL");
            }
            tv.setText(String.valueOf(listItem.get(position).id)
                    + ". " + listItem.get(position).name
                    + " , " +listItem.get(position).email);



            return row;
        }




    }

    private class listItem {
     Integer id;
     String email;
     String name;

    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        private static final String TAG = "HTTPAsyncTask";
        ArrayList<listItem> items= new ArrayList<listItem>();

        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {


           // Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            Log.e("STRING RECIEVED",result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray itemsArray = jsonObject.getJSONArray("items");
                for(int i = 0; i<itemsArray.length(); i++){
                    final JSONObject book = itemsArray.getJSONObject(i);
                    listItem li= new listItem();
                    li.id =Integer.parseInt(book.getString("id"));
                    li.email = book.getString("emailAddress");
                    li.name = "no name";
                    if (book.has("name"))
                    {
                        li.name = book.getString("name");
                    }
                    items.add(li);

                    Log.e("ID+++++", book.getString("id"));

                }

                lv.setAdapter(new TestAdapter(MainActivity.this, items));

            } catch (JSONException e) {
                try {
                    final JSONObject book = new JSONObject(result);
                    listItem li = new listItem();
                    li.id = Integer.parseInt(book.getString("id"));
                    li.email = book.getString("emailAddress");
                    li.name = "no name";
                    if (book.has("name")) {
                        li.name = book.getString("name");
                    }
                    items.add(li);

                    Log.e("ID+++++", book.getString("id"));
                    lv.setAdapter(new TestAdapter(MainActivity.this, items));
                }
                catch (JSONException a) {

                }
            }





        }
    }
}
