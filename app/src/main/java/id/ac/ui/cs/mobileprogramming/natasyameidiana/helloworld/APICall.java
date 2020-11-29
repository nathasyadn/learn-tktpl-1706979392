package id.ac.ui.cs.mobileprogramming.natasyameidiana.helloworld;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class APICall extends AsyncTask<String, String, String> {

    private String data;

    public APICall(String data) {
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String wifiList = data;
        BufferedReader reader = null;
        try {
            URL url = new URL("https://8093aaa469fffa4c9e4fd36c60e8530e.m.pipedream.net");

            String data = URLEncoder.encode("wifi", "UTF-8")
                    + "=" + URLEncoder.encode(wifiList, "UTF-8");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            try {
                reader.close();
            } catch (Exception ignored) {
            }
        }

    }
}
