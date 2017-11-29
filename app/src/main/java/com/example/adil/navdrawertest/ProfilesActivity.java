package com.example.adil.navdrawertest;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import static com.example.adil.navdrawertest.MyDBHandler.TABLE_PROFILES;


public class ProfilesActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> profileList;
    private ProfileCustomAdapter adapter;




    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);   // content is page of all profiles
        profileList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.profileListID);   // setting up variables

        updateProfiles();


        adapter= new ProfileCustomAdapter(this,profileList);  // setting up adapter
        listView.setAdapter(adapter);

        System.out.println("DO U GO ON CREATE AFTER CREATING PROFILE?? LET ME KNOW OK");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent editorLaunchInterest = new Intent(getApplicationContext(), ChoreEditorActivity.class); // what happens when you press on a profile
                startActivity(editorLaunchInterest);
            }
        });
    }





    public void addProfile(String profile)
    {

        profileList.add(profile);
        adapter= new ProfileCustomAdapter(this,profileList);  // setting up adapter
        listView.setAdapter(adapter);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) // gets data from new profile creation
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 0){
            Log.e("CHECK", "We are in onactivity PROFILES");
            addProfile(data.getStringExtra("profileName"));
            addProfileToXMLFile(data.getStringExtra("profileName"));
        }
    }

    //protected void addAllToProfileList(ArrayList<String> pro)


    private void updateProfiles(){
        MyDBHandler dbHandler = new MyDBHandler(this);
        Cursor res = dbHandler.getAllProfiles();

        while(res.moveToNext()){
            profileList.add(res.getString(1));
            System.out.println(res.getString(1));
        }
    }

    private void addProfileToXMLFile(String name) {
        try {
            System.out.println("Are we in the xml editor??");
            String filepath = "C:\\Users\\Adil\\Desktop\\MusicPlayer\\ChoreTracker\\app\\src\\main\\res\\values\\strings.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            Node res = doc.getFirstChild();
            Node staff = doc.getElementsByTagName("string-array").item(0);

            Element profile = doc.createElement("item");
            profile.appendChild(doc.createTextNode(name));
            staff.appendChild(profile);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

        ////////////////  ADD PLUS BUTTON
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
                                                                  // THIS ADDS PLUS BUTTON
        getMenuInflater().inflate(R.menu.add_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
                                                                                            //      CREATING NEW PROFILE
        int id = item.getItemId();

        if (id == R.id.action_settings2) {
            Intent myIntent = new Intent(ProfilesActivity.this, CreateProfile.class);
            startActivityForResult(myIntent, 0);
        }
        if (id == R.id.action_settings3) {
            Intent myIntent = new Intent(ProfilesActivity.this, CreateProfile.class);
            startActivityForResult(myIntent, 0);
        }
        return super.onOptionsItemSelected(item);
    }

}
