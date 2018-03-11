package edu.uco.avalon;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphViewActivity extends AppCompatActivity {

    GraphView graphView;
    Button btnBar;
    Button btnLine;
    Button btnComb;
    int equipCost;
    int maintCost;
    int totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphview);

        graphView = (GraphView)findViewById(R.id.graph_view);
        graphView.getViewport().setScrollable(true); // enables horizontal scrolling
        graphView.getViewport().setScrollableY(true);
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(4);
        btnBar = (Button)findViewById(R.id.btn_bar);
        btnLine = (Button)findViewById(R.id.btn_line);
        btnComb = (Button)findViewById(R.id.btn_comb);

        equipCost = Integer.parseInt(getIntent().getStringExtra("EQUIP"));
        maintCost = Integer.parseInt(getIntent().getStringExtra("MAINT"));
        totalCost = Integer.parseInt(getIntent().getStringExtra("TOTAL"));

        btnBar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                graphView.removeAllSeries();
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, equipCost)
                });
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(2, maintCost)
                });
                BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(3, totalCost)
                });
                graphView.addSeries(series);
                graphView.addSeries(series2);
                graphView.addSeries(series3);
                series.setColor(Color.GREEN);
                series2.setColor(Color.BLUE);
                series3.setColor(Color.RED);
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.BLACK);
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.BLACK);
                series3.setDrawValuesOnTop(true);
                series3.setValuesOnTopColor(Color.BLACK);
                series.setTitle("EQUIP");
                series2.setTitle("MAINT");
                series3.setTitle("TOTAL");
                graphView.getLegendRenderer().setVisible(true);
                graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            }
        });
        btnLine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                graphView.removeAllSeries();
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(
                        new DataPoint[] {
                                new DataPoint(0.5,equipCost/10),
                                new DataPoint(1,equipCost/8),
                                new DataPoint(1.5,equipCost/6),
                                new DataPoint(2,equipCost/4),
                                new DataPoint(2.5,equipCost/2),
                                new DataPoint(3,equipCost)
                        }
                );
                series.setColor(Color.GREEN);
                LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(
                        new DataPoint[] {
                                new DataPoint(0.5,maintCost/10),
                                new DataPoint(1,maintCost/8),
                                new DataPoint(1.5,maintCost/6),
                                new DataPoint(2,maintCost/4),
                                new DataPoint(2.5,maintCost/2),
                                new DataPoint(3,maintCost)
                        }
                );
                series2.setColor(Color.BLUE);
                LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(
                        new DataPoint[] {
                                new DataPoint(0.5,totalCost/10),
                                new DataPoint(1, totalCost/8),
                                new DataPoint(1.5,totalCost/6),
                                new DataPoint(2,totalCost/4),
                                new DataPoint(2.5,totalCost/2),
                                new DataPoint(3,totalCost)
                        }
                );
                series3.setColor(Color.RED);
                graphView.addSeries(series);
                graphView.addSeries(series2);
                graphView.addSeries(series3);
                series.setTitle("EQUIP");
                series2.setTitle("MAINT");
                series3.setTitle("TOTAL");
                graphView.getLegendRenderer().setVisible(true);
                graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            }
        });
        btnComb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                graphView.removeAllSeries();
                graphView.removeAllSeries();
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(
                        new DataPoint[] {
                                new DataPoint(0.5,equipCost/10),
                                new DataPoint(1,equipCost/8),
                                new DataPoint(1.5,equipCost/6),
                                new DataPoint(2,equipCost/4),
                                new DataPoint(2.5,equipCost/2),
                                new DataPoint(3,equipCost)
                        }
                );
                series.setColor(Color.GREEN);
                LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(
                        new DataPoint[] {
                                new DataPoint(0.5,maintCost/10),
                                new DataPoint(1,maintCost/8),
                                new DataPoint(1.5,maintCost/6),
                                new DataPoint(2,maintCost/4),
                                new DataPoint(2.5,maintCost/2),
                                new DataPoint(3,maintCost)
                        }
                );
                series2.setColor(Color.BLUE);
                LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(
                        new DataPoint[] {
                                new DataPoint(0.5,totalCost/10),
                                new DataPoint(1, totalCost/8),
                                new DataPoint(1.5,totalCost/6),
                                new DataPoint(2,totalCost/4),
                                new DataPoint(2.5,totalCost/2),
                                new DataPoint(3,totalCost)
                        }
                );
                series3.setColor(Color.RED);
                BarGraphSeries<DataPoint> series4 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, equipCost)
                });
                series4.setColor(Color.GREEN);
                BarGraphSeries<DataPoint> series5 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(2, maintCost)
                });
                series5.setColor(Color.BLUE);
                BarGraphSeries<DataPoint> series6 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(3, totalCost)
                });
                series6.setColor(Color.RED);
                graphView.addSeries(series4);
                graphView.addSeries(series5);
                graphView.addSeries(series6);
                graphView.addSeries(series);
                graphView.addSeries(series2);
                graphView.addSeries(series3);


                graphView.getLegendRenderer().setVisible(false);
            }
        });
    }

}
