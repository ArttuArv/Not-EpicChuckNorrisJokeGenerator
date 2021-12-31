package not.epic.chuck.norris.joke.generator;


/********************************************************************


 Made as a exercise to use APIs. Most of the code came from
 the great Interwebs. Thanks.
 *******************************************************************/

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView jokeText;
    Button btnGetJoke;

    // URL string
    String myUrl = "https://api.chucknorris.io/jokes/random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokeText = findViewById( R.id.textJoke );
        btnGetJoke = findViewById( R.id.button );

        btnGetJoke.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                getData();
            }
        });
    }

    private void getData() {

        StringRequest myRequest = new StringRequest( Request.Method.GET, myUrl, response -> {
            try {
                //Create a JSON object containing information from the API
                JSONObject myJsonObject = new JSONObject( response );
                jokeText.setText( myJsonObject.getString("value" ) );
            } catch ( JSONException e ) {
                e.printStackTrace();
            }
        }, volleyError -> Toast.makeText( MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT ).show() );

        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( myRequest );
    }
}