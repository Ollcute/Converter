package com.example.converter_shevtsova_303;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //объявление переменных
    private Spinner spFrom;
    private Spinner spTo;
    private EditText etFrom;
    private TextView tvTo;
    //инициализация адаптера
    private ArrayAdapter<Unit> adp;
    private ArrayList<Unit> Lengths = new ArrayList<>();
    private ArrayList<Unit> Squares = new ArrayList<>();
    private ArrayList<Unit> Weights = new ArrayList<>();
    //объявление группы  RadioButton
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //переменные объявленные выше соединяем с физ компонентами экрана
        spTo = findViewById(R.id.spinner_To);
        spFrom = findViewById(R.id.spinner_From);
        etFrom = findViewById(R.id.editText_From);
        tvTo = findViewById(R.id.tv_Result);
        RadioGroup radioGroup = findViewById(R.id.radioGroup_Over);
        //создание адаптера для спиннера параметризированного созданным классом Unit
        adp = new ArrayAdapter<Unit>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //добавление элемента в массив с названием и коэффициентом
        //длина
        Lengths.add(new Unit( "mm",0.001));
        Lengths.add(new Unit( "cm",0.01));
        Lengths.add(new Unit( "m",0.1));
        Lengths.add(new Unit( "km",1.0));
        //вес
        Weights.add(new Unit("mg", 0.001));
        Weights.add(new Unit("g", 1.0));
        Weights.add(new Unit("kg", 1000.0));
        Weights.add(new Unit("t", 1000000.0));
        //площадь
        Squares.add(new Unit("mm2", 0.000001));
        Squares.add(new Unit("cm2", 0.000001));
        Squares.add(new Unit("m2", 0.000001));
        Squares.add(new Unit("km2", 0.000001));
        //добавление в адаптер всех элементов массива Lengths
        adp.addAll(Lengths);

        spFrom.setAdapter(adp);//подключение адаптера к спиннеру From
        spTo.setAdapter(adp);//подключение адаптера к спиннеру To



        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //метод отслеживания включения переключателя, при выборе одного из переключателей происходит очищение адаптера и заполнение его новыми данными из массива
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rBut_lenght:
                        adp.clear();
                        adp.addAll(Lengths);
                        break;
                    case R.id.rBut_Weight:
                        adp.clear();
                        adp.addAll(Weights);
                        break;
                    case R.id.rBut_Square:
                        adp.clear();
                        adp.addAll(Squares);
                        break;
                }
            }
        });



    }
    //метод нажатия на кнопку, при котором происходит расчет значения
    public void onClick(View view)  {

        if (!etFrom.getText().toString().isEmpty()) {
            Double result = Double.parseDouble(etFrom.getText().toString()) *
                    (adp.getItem(spFrom.getSelectedItemPosition()).coeff  / adp.getItem(spTo.getSelectedItemPosition()).coeff );
            tvTo.setText(result.toString());
        } else {
            Toast.makeText(getApplicationContext(), "Поле не должно быть пустым", Toast.LENGTH_SHORT).show();
        }

    }

}