package com.example.mohsiul.simplerxjava;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = ".MainActivity";
    TextView textView;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private Disposable disposable;
    EditText editText;

    ArrayList<Animal_name> exampleList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.recyclerView);
        textView=findViewById(R.id.textview1);
        editText=findViewById(R.id.editText);

        //Observable

        Observable<String> animalObservable=getAnimalObservable();

        //Observer

        Observer<String> animalObserver=getAnimalObserver();

        //subscription

        animalObservable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(animalObserver);
        
        

    }



    private Observer<String> getAnimalObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe:");
            }

            @Override
            public void onNext(String s) {

                Log.d(TAG, "onNext: Name:"+s);
                //textView.setText(s);

                //ArrayList<Animal_name> exampleList= new ArrayList<>();

                exampleList.add(new Animal_name(s));

                mRecyclerView.setHasFixedSize(true);
                mLayoutManager=new LinearLayoutManager(MainActivity.this);
                mAdapter=new RecyclerViewAdapter(exampleList);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);


            }


            @Override
            public void onError(Throwable e) {

                Log.e(TAG, "onError: "+e.getMessage() );

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: All animals are emited");

            }



        };

    }

    private Observable<String> getAnimalObservable() {

       //final List<String>notes=prepareNotes();


       return Observable.just("Ant","Cat","Dog","Fox","Bee");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
