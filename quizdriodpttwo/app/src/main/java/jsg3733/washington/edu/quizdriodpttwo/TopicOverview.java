package jsg3733.washington.edu.quizdriodpttwo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;


public class TopicOverview extends ActionBarActivity {
    private String topicName;
    private int QNum;
    private int howManyRight;
    private String answer;
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);

        Intent launchedMe = getIntent();
        topicName = launchedMe.getStringExtra("topic");
        QNum = 1;
        answer = "";
        response = "4";
        howManyRight = 0;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new TopicOverFragment())
                    .commit();
            /*if(false){
                //QuestionFragment qqq =  (QuestionFragment) getSupportFragmentManager().findFragmentById(R.id.fragQ);
                //qqq.setText("Hello Jacob");
                //View fff = qqq.getView();
                //TextView ttt = (TextView) fff.findViewById(R.id.txtHello);
                //ttt.setText("Yay");

                //qqq.setText("Today is today");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new QuestionFragment())
                        .commit();
            }*/
        }

        final Button begin = (Button) findViewById(R.id.btnNext);
        //final String btnText = begin.getText().toString();
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String btnText = begin.getText().toString();
                //if(btnText.equals("Begin")){
                if(begin.getText().toString().equals("Begin")){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new QuestionFragment())
                            .commit();
                            begin.setText("Submit");
                            //btnText = begin.getText().toString();
                }else if(begin.getText().toString().equals("Submit")) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new AnswerFragment())
                            .commit();
                            //begin.setText("Next");
                           // btnText = begin.getText().toString();
                            QNum++;
                            if(QNum > 5) {
                                begin.setText("Finish");
                            }else {
                                begin.setText("Next");
                            }
                }else if(begin.getText().toString().equals("Next")) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new QuestionFragment())
                            .commit();
                            begin.setText("Submit");
                            //btnText = begin.getText().toString();
                }else {
                    Intent nextActivity = new Intent(TopicOverview.this, Topics.class);
                    startActivity(nextActivity);
                    finish();
                }

                /*getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new QuestionFragment())
                        .commit();*/
            }
        });
    }

    public String getTopic(){
        return topicName;
    }

    public int getQuestionNum(){
        return QNum;
    }

    public String getResponse(){
        return response;
    }

    public void setResponse(String res) {
        response = res;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String ans){
        answer = ans;
    }

    public int getHowManyRight(){
        return howManyRight;
    }

    public void addOneToAmountRight(){
        howManyRight++;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class TopicOverFragment extends Fragment {

        public TopicOverFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View topicOverviewView = inflater.inflate(R.layout.fragment_topic_overview, container, false);
            TopicOverview activity = (TopicOverview) getActivity();
            String topicName = activity.getTopic();

            TextView header = (TextView) topicOverviewView.findViewById(R.id.txtTopic);
            header.setText(topicName);
            TextView description = (TextView) topicOverviewView.findViewById(R.id.txtDesc);
            TextView questions = (TextView) topicOverviewView.findViewById(R.id.txtQuestions);

            if(topicName.equals("Math")) {
                description.setText("A topic based on the study of mathematics." +
                        "Includes the use of numbers and formulas.");
                questions.setText("Questions: 5");
            }else if(topicName.equals("Physics")) {
                description.setText("A topic based on the study of the natural science." +
                        "This includes topics such as motion and energy.");
                questions.setText("Questions: 5");
            }else if(topicName.equals("Marvel Super Heroes")) {
                description.setText("A topic based on the super heroes within the Marvel comics.");
                questions.setText("Questions: 5");
            }else if(topicName.equals("Hockey")){
                description.setText("A topic based on the sport hockey which uses a stick" +
                        " and puck to score goals.");
                questions.setText("Questions: 5");
            }else if(topicName.equals("Geography")) {
                description.setText("A topic based on where different cities and rivers are located.");
                questions.setText("Questions: 5");
            }

            return topicOverviewView;
        }
    }

    public static class QuestionFragment extends Fragment {
        private Button submit;
        //private String response;
        private TopicOverview activity;

        public QuestionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View questionView = inflater.inflate(R.layout.fragment_question, container, false);

            activity = (TopicOverview)getActivity();
            String topicName = activity.getTopic();
            int QNum = activity.getQuestionNum();
            String answer = "";
            TextView question = (TextView) questionView.findViewById(R.id.txtQuestion);
            RadioButton responseOne = (RadioButton) questionView.findViewById(R.id.btnResponseOne);
            RadioButton responseTwo = (RadioButton) questionView.findViewById(R.id.btnResponseTwo);
            RadioButton responseThree = (RadioButton) questionView.findViewById(R.id.btnResponseThree);
            RadioButton responseFour = (RadioButton) questionView.findViewById(R.id.btnResponseFour);
            //String topicName = "Math";
            //int QNum = 1;
            //time.setText("hehehehe");

            if(topicName.equals("Math")) {
                if(QNum == 1){
                    question.setText("2 + 2 =");
                    responseOne.setText("1");
                    responseTwo.setText("2");
                    responseThree.setText("3");
                    responseFour.setText("4"); //Answer
                    answer = responseFour.getText().toString();
                }else if(QNum == 2) {
                    question.setText("2 x 2 =");
                    responseOne.setText("1");
                    responseTwo.setText("2");
                    responseThree.setText("3");
                    responseFour.setText("4"); //Answer
                    answer = responseFour.getText().toString();
                }else if(QNum == 3) {
                    question.setText("2 - 2 =");
                    responseOne.setText("1");
                    responseTwo.setText("2");
                    responseThree.setText("0");  //Answer
                    responseFour.setText("4");
                    answer = responseThree.getText().toString();
                }else if(QNum == 4) {
                    question.setText("1 + 1 =");
                    responseOne.setText("1");
                    responseTwo.setText("2");  //Answer
                    responseThree.setText("3");
                    responseFour.setText("4");
                    answer = responseTwo.getText().toString();
                }else if(QNum == 5) {
                    question.setText("1 x 1 =");
                    responseOne.setText("1");  //Answer
                    responseTwo.setText("2");
                    responseThree.setText("3");
                    responseFour.setText("4");
                    answer = responseOne.getText().toString();
                }
            }else if(topicName.equals("Physics")) {
                if(QNum == 1){
                    question.setText("It is easier to roll a stone up a sloping road than to lift it vertical upwards because ");
                    responseOne.setText("work done in rolling is more than in lifting");
                    responseTwo.setText("work done in lifting the stone is equal to rolling it");
                    responseThree.setText("work done in both is same but the rate of doing work is less in rolling");
                    responseFour.setText("work done in rolling a stone is less than in lifting it"); //answer
                    answer = responseFour.getText().toString();
                }else if(QNum == 2) {
                    question.setText("The absorption of ink by blotting paper involves");
                    responseOne.setText("viscosity of ink");
                    responseTwo.setText("capillary action phenomenon"); //answer
                    responseThree.setText("diffusion of ink through the blotting");
                    responseFour.setText("siphon action");
                    answer = responseTwo.getText().toString();
                }else if(QNum == 3) {
                    question.setText("The circumference of a circle is");
                    responseOne.setText("who cares");
                    responseTwo.setText("4");
                    responseThree.setText("2piR"); //Answer
                    responseFour.setText("3");
                    answer = responseThree.getText().toString();

                }else if(QNum == 4) {
                    question.setText("A square is made up of how many connected lines");
                    responseOne.setText("1");
                    responseTwo.setText("2");
                    responseThree.setText("3");
                    responseFour.setText("4"); //answer
                    answer  = responseFour.getText().toString();
                }else if(QNum == 5) {
                    question.setText("The area of a square is");
                    responseOne.setText("1s");
                    responseTwo.setText("2s");
                    responseThree.setText("s x s"); //answer
                    responseFour.setText("5");
                    answer = responseThree.getText().toString();
                }
            }else if(topicName.equals("Marvel Super Heroes")) {
                if(QNum == 1){
                    question.setText("What color was the hulk originally in comic books");
                    responseOne.setText("green");
                    responseTwo.setText("grey"); //answer
                    responseThree.setText("purple");
                    responseFour.setText("teal");
                    answer = responseTwo.getText().toString();
                }else if(QNum == 2) {
                    question.setText("Who vetoed the option of Wolverine being an actual mutated wolverine");
                    responseOne.setText("Stan Lee");  //answer
                    responseTwo.setText("Peter Pan");
                    responseThree.setText("George Lucas");
                    responseFour.setText("You");
                    answer  = responseOne.getText().toString();
                }else if(QNum == 3) {
                    question.setText("Who looked into buying Marvel in the late 90s because they wanted to play Spider-Man");
                    responseOne.setText("Mickey Mouse");
                    responseTwo.setText("George Lucas");
                    responseThree.setText("Micheal Jackson"); //answer
                    responseFour.setText("Will Ferrel");
                    answer = responseThree.getText().toString();
                }else if(QNum == 4) {
                    question.setText("By how many years did Loki predate his brother Thor in comic books");
                    responseOne.setText("1");
                    responseTwo.setText("18");
                    responseThree.setText("13"); //answer
                    responseFour.setText("7");
                    answer = responseThree.getText().toString();
                }else if(QNum == 5) {
                    question.setText("What character did Marvel keep using after the license expired in the mid-80s for them");
                    responseOne.setText("Godzilla");// Answer
                    responseTwo.setText("The Hulk");
                    responseThree.setText("Iron Man");
                    responseFour.setText("Scooby Doo");
                    answer = responseOne.getText().toString();
                }
            }else if(topicName.equals("Hockey")){
                if(QNum == 1){
                    question.setText("How many goals is a hat-trick");
                    responseOne.setText("1");
                    responseTwo.setText("2");
                    responseThree.setText("3"); //answer
                    responseFour.setText("4");
                    answer = responseThree.getText().toString();
                }else if(QNum == 2) {
                    question.setText("What is between both blue lines called");
                    responseOne.setText("Center ice");
                    responseTwo.setText("The neutral zone"); //answer
                    responseThree.setText("Crease");
                    responseFour.setText("Face-off");
                    answer = responseTwo.getText().toString();
                }else if(QNum == 3) {
                    question.setText("Where is the goalie usually positioned");
                    responseOne.setText("the crease"); //answer
                    responseTwo.setText("center ice");
                    responseThree.setText("slot");
                    responseFour.setText("face-off circle");
                    answer = responseOne.getText().toString();
                }else if(QNum == 4) {
                    question.setText("How many assists is a play-maker");
                    responseOne.setText("1");
                    responseTwo.setText("2");
                    responseThree.setText("3"); //answer
                    responseFour.setText("4");
                    answer = responseThree.getText().toString();
                }else if(QNum == 5) {
                    question.setText("How many forwards are on the ice at one time");
                    responseOne.setText("1");
                    responseTwo.setText("2");
                    responseThree.setText("3");
                    responseFour.setText("4"); //answer
                    answer  = responseFour.getText().toString();
                }
            }else if(topicName.equals("Geography")) {
                if(QNum == 1){
                    question.setText("What is the capital of Washington");
                    responseOne.setText("Kennewick");
                    responseTwo.setText("Seattle");
                    responseThree.setText("Tacoma");
                    responseFour.setText("Olympia"); //answer
                    answer = responseFour.getText().toString();
                }else if(QNum == 2) {
                    question.setText("What is the capital of Oregon ");
                    responseOne.setText("Salem"); //answer
                    responseTwo.setText("Portland");
                    responseThree.setText("Beaverton");
                    responseFour.setText("Eugene");
                    answer = responseOne.getText().toString();
                }else if(QNum == 3) {
                    question.setText("Where is the University of Washington located");
                    responseOne.setText("Spokane");
                    responseTwo.setText("Bellevue");
                    responseThree.setText("Seattle"); //answer
                    responseFour.setText("Albany");
                    answer = responseThree.getText().toString();
                }else if(QNum == 4) {
                    question.setText("What is the capital of New York");
                    responseOne.setText("Albany"); //answer
                    responseTwo.setText("Buffalo");
                    responseThree.setText("New York City");
                    responseFour.setText("Brooklyn");
                    answer = responseOne.getText().toString();
                }else if(QNum == 5) {
                    question.setText("What is the capital of California");
                    responseOne.setText("Sacramento"); //answer
                    responseTwo.setText("Los Angeles");
                    responseThree.setText("San Jose");
                    responseFour.setText("San Francisco");
                    answer = responseOne.getText().toString();
                }
            }

            submit = (Button) activity.findViewById(R.id.btnNext);
            submit.setEnabled(false);

            responseOne.setOnClickListener(btnListener);
            responseTwo.setOnClickListener(btnListener);
            responseThree.setOnClickListener(btnListener);
            responseFour.setOnClickListener(btnListener);
            //String response = "";

            activity.setAnswer(answer);
            //activity.setResponse(response);

            return questionView;
        }

        /*public String getQResponse(){
            return response;
        }*/

        private View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("QuestionPage", answer);
                boolean checked = ((RadioButton) v).isChecked();
                //TopicOverview activity = (TopicOverview)getActivity();
                String response;
                submit.setEnabled(true);
                switch(v.getId()) {
                    case R.id.btnResponseOne:
                        if(checked) {
                            response = ((RadioButton) getActivity().findViewById(R.id.btnResponseOne)).getText().toString();
                            activity.setResponse(response);
                        }
                        break;
                    case R.id.btnResponseTwo:
                        if(checked) {
                            response = ((RadioButton) getActivity().findViewById(R.id.btnResponseTwo)).getText().toString();
                            activity.setResponse(response);
                        }
                        break;
                    case R.id.btnResponseThree:
                        if(checked) {
                            response = ((RadioButton) getActivity().findViewById(R.id.btnResponseThree)).getText().toString();
                            activity.setResponse(response);
                        }
                        break;
                    case R.id.btnResponseFour:
                        if(checked) {
                            response = ((RadioButton) getActivity().findViewById(R.id.btnResponseFour)).getText().toString();
                            activity.setResponse(response);
                        }
                        break;
                }
            }
        };

        /*public void setText(String text) {
            TextView t = (TextView) getView().findViewById(R.id.txtQuestion);  //UPDATE
            t.setText(text);
        }*/
    }

    public static class AnswerFragment extends Fragment {

        public AnswerFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View answerView = inflater.inflate(R.layout.fragment_answer_summary, container, false);
            TopicOverview activity = (TopicOverview) getActivity();
            //String topicName = activity.getTopic();
            String response = activity.getResponse();
            String answer = activity.getAnswer();

            TextView lastResponse = (TextView) answerView.findViewById(R.id.txtLastResponse);
            lastResponse.setText("Your response was: " + response);
            TextView correctAnswer = (TextView) answerView.findViewById(R.id.txtAnswer);
            correctAnswer.setText("The correct answer is: " + answer);
            TextView amountRight = (TextView) answerView.findViewById(R.id.txtTotalRight);

            if(response.equals(answer)) {
                activity.addOneToAmountRight();
            }
            int howManyRight = activity.getHowManyRight();
            amountRight.setText("You have " + howManyRight + " out of " + "5" + " correct!");

            return answerView;
        }
    }

}
