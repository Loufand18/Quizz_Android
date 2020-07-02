package com.example.appresume.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appresume.Model.Question;
import com.example.appresume.Model.QuestionBank;
import com.example.appresume.R;

import org.xml.sax.ext.Locator2;

import java.util.Arrays;

public class GameActivity extends Activity implements View.OnClickListener {
   private QuestionBank mQuestionBank;
   private TextView mtext;
   private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private int mScore;
    private int mNbQuestion;
    private Question currentQuestion;
    private boolean mEvent;
    public static final String Bundle_Extra_Score="Bundle_Extra_Score";
    public static final String Bundle_Score="Bundle_Score";
    public static final String Bundle_Nb="Bundle_Nb";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mtext=(TextView)findViewById(R.id.text);
        button1=(Button)findViewById(R.id.answer1_btn);
        button2=(Button)findViewById(R.id.answer2_btn);
        button3=(Button)findViewById(R.id.answer3_btn);
        button4=(Button)findViewById(R.id.answer4_btn);
        button1.setTag(0);
        button2.setTag(1);
        button3.setTag(2);
        button4.setTag(3);
        mQuestionBank=this.generateQuestions();
        currentQuestion=mQuestionBank.getQuestion();
        this.displayQuestion(currentQuestion);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        mEvent=true;
        if (savedInstanceState!=null){
            mScore=savedInstanceState.getInt(Bundle_Score);
            mNbQuestion=savedInstanceState.getInt(Bundle_Nb);
        }
        else{
            mScore=0;
            mNbQuestion=10;
        }




    }

    /**
     * Called to retrieve per-instance state from an activity before being killed
     * so that the state can be restored in {@link #onCreate} or
     * {@link #onRestoreInstanceState} (the {@link Bundle} populated by this method
     * will be passed to both).
     *
     * <p>This method is called before an activity may be killed so that when it
     * comes back some time in the future it can restore its state.  For example,
     * if activity B is launched in front of activity A, and at some point activity
     * A is killed to reclaim resources, activity A will have a chance to save the
     * current state of its user interface via this method so that when the user
     * returns to activity A, the state of the user interface can be restored
     * via {@link #onCreate} or {@link #onRestoreInstanceState}.
     *
     * <p>Do not confuse this method with activity lifecycle callbacks such as {@link #onPause},
     * which is always called when the user no longer actively interacts with an activity, or
     * {@link #onStop} which is called when activity becomes invisible. One example of when
     * {@link #onPause} and {@link #onStop} is called and not this method is when a user navigates
     * back from activity B to activity A: there is no need to call {@link #onSaveInstanceState}
     * on B because that particular instance will never be restored,
     * so the system avoids calling it.  An example when {@link #onPause} is called and
     * not {@link #onSaveInstanceState} is when activity B is launched in front of activity A:
     * the system may avoid calling {@link #onSaveInstanceState} on activity A if it isn't
     * killed during the lifetime of B since the state of the user interface of
     * A will stay intact.
     *
     * <p>The default implementation takes care of most of the UI per-instance
     * state for you by calling {@link View#onSaveInstanceState()} on each
     * view in the hierarchy that has an id, and by saving the id of the currently
     * focused view (all of which is restored by the default implementation of
     * {@link #onRestoreInstanceState}).  If you override this method to save additional
     * information not captured by each individual view, you will likely want to
     * call through to the default implementation, otherwise be prepared to save
     * all of the state of each view yourself.
     *
     * <p>If called, this method will occur after {@link #onStop} for applications
     * targeting platforms starting with {@link Build.VERSION_CODES#P}.
     * For applications targeting earlier platform versions this method will occur
     * before {@link #onStop} and there are no guarantees about whether it will
     * occur before or after {@link #onPause}.
     *
     * @param outState Bundle in which to place your saved state.
     * @see #onCreate
     * @see #onRestoreInstanceState
     * @see #onPause
     */

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Bundle_Score,mScore);
        outState.putInt(Bundle_Nb,mNbQuestion);
    }

    private void displayQuestion(final Question question ){
        mtext.setText(question.getmQuestion());
        button1.setText(question.getmChoiceList().get(0));
        button2.setText(question.getmChoiceList().get(1));
        button3.setText(question.getmChoiceList().get(2));
        button4.setText(question.getmChoiceList().get(3));

    }
    private QuestionBank generateQuestions(){
        Question question1 = new Question("Qui est la personne la plus reguliere sur les cours?",
                Arrays.asList("Moussa Deme",
                        "Katy Diop Boye",
                        "Khoudia Diouf",
                        "Marie jeanne Sarr"),
                0);

        Question question2 = new Question("Qui est la personne qui a toujours mauvaise mine,qui a toujours envie de dormir,qui ne finit jamais un travail de groupe?",
                Arrays.asList("Khoudia Diouf",
                        "Mamousse Ndiaye",
                        "Mbaye Thilor Sene",
                        "Yacine Toure"),
                1);

        Question question3 = new Question("Qui ramasse toujours le TD des autres lors des groupes de Travail?",
                Arrays.asList("Issa Ndong",
                        "Fallou Ndiaye",
                        "Cheikh Mbacke",
                        "Ousseynou Ndongo"),
                2);
        Question question4 = new Question("Qu'est ce qui ne rang jamais au Bus, qui se faufille  toujours devant ?",
                Arrays.asList("Issa Ndong",
                        "Fallou Ndiaye",
                        "Matar Diop",
                        "Marie Jeanne Sarr"),
                3);
        Question question5 = new Question("Qui est le coureur de Jupon  le plus titre?",
                Arrays.asList("Bamba Diagne",
                        "Khalipha",
                        "Cheikh Mbacke",
                        "Ousseynou Ndongo"),
                0);
        Question question6 = new Question("Qui est la personne qui la plus indisciplinee et comique dans ses paroles?",
                Arrays.asList("Mbaye Thilor Sene",
                        "Fallou Ndiaye",
                        "Khalipha",
                        "Yacine Toure"),
                0);
        Question question7 = new Question("Qui est la personne qui seme toujours la pagaille(musique,comedie) lors des groupes de travail ?",
                Arrays.asList("Bamba Diagne",
                        "Arame",
                        "Khalipha",
                        "Ousseynou Ndongo"),
                2);
        Question question8 = new Question("Qui est la personne plus manquante de A2V et qui traite les autres de manquants  ?",
                Arrays.asList("Yacine Toure",
                        "Issa Ndong",
                        "Arame",
                        "Ousseynou Ndongo"),
                0);
        Question question9 = new Question("Qui est la personne la plus drole (par ses faits et gestes),qui en la voyant on a deja envie de rire  ?",
                Arrays.asList("Mbaye Thilor Sene",
                        "Issa Ndong",
                        "Cheikh Mbacke",
                        "Boubacar "),
                2);
        Question question10 = new Question("Qui surnomme t-on Tortue Nimza de par son gros nez  ?",
                Arrays.asList("Boubacar",
                        "Matar Diop",
                        "Issa Ndong",
                        "Ousseynou Ndongo "),
                1);


        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10
                ));
    }


    public void onClick(View v) {
         int responseIndex= (int)v.getTag();
         if(responseIndex==currentQuestion.getmAnswerIndex()){
             Toast.makeText(this,"Correct ",Toast.LENGTH_SHORT).show();
             mScore++;
         }
         else{
             Toast.makeText(this," Fausse",Toast.LENGTH_SHORT).show();
         }
         mEvent=false;
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 mEvent=true;
                 if (--mNbQuestion==0){
                     endGame();
                 }
                 else{
                     currentQuestion=mQuestionBank.getQuestion();
                     displayQuestion(currentQuestion);
                 }
             }
         },2000);

    }


    /**
     * Called to process touch screen events.  You can override this to
     * intercept all touch screen events before they are dispatched to the
     * window.  Be sure to call this implementation for touch screen events
     * that should be handled normally.
     *
     * @param ev The touch screen event.
     * @return boolean Return true if this event was consumed.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEvent && super.dispatchTouchEvent(ev);
    }

    private void endGame(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Bien joue !")
                .setMessage("Ton score est  "+ mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent();
                        intent.putExtra(Bundle_Extra_Score,mScore);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                })
                .create()
                .show();
    }




}
