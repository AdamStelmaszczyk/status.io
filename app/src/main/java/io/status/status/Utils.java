package io.status.status;

import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Adam Stelmaszczyk
 */
public class Utils {

    public static String responseToString(HttpResponse httpResponse) {
        InputStream inputStream;
        try {
            inputStream = httpResponse.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            return convertInputStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        inputStream.close();
        return result;
    }
}
