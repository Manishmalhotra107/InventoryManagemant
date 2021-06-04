package com.yashu.arora.inventorymanagement

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yashu.arora.inventorymanagement.data.InventoryDatabase
import com.yashu.arora.inventorymanagement.data.ProductWithStockIn
import com.yashu.arora.inventorymanagement.data.ProductWithStockOut
import dagger.android.AndroidInjection
import lecho.lib.hellocharts.listener.ComboLineColumnChartOnValueSelectListener
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils
import lecho.lib.hellocharts.view.ComboLineColumnChartView
import lecho.lib.hellocharts.view.PieChartView
import javax.inject.Inject


class PieChartActivity : AppCompatActivity() {

    @Inject
    lateinit var inventoryDatabase:InventoryDatabase

    private var chart: ComboLineColumnChartView? = null
    private var data: ComboLineColumnChartData? = null
    private var availableQuaintity:Int =0
    private var stockOutQuaintity:Int =0
    private var stockInQuaintity:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_pie_chart_layout)
      val productList =  inventoryDatabase.productDao().getProduct()

        productList.forEach { product ->
            availableQuaintity+=product.quantity
        }
        var productStockInList =  ProductWithStockIn.getProductStockIn(inventoryDatabase.productDao().getProductWithStockIn())

        productStockInList?.subscribe {
            it.forEach { product ->
            stockInQuaintity += product?.quantity?:0
        }
        }
        var productStockOutList =  ProductWithStockOut.getProductStockOutList(inventoryDatabase.productDao().getProductWithStockOut())

        productStockOutList?.subscribe {
            it.forEach { product ->
                stockOutQuaintity += product?.quantity?:0
            }
        }
        val pieChartView = findViewById<PieChartView>(R.id.chart)
        val pieData: ArrayList<SliceValue> = arrayListOf()
        pieData.add(SliceValue(availableQuaintity.toFloat(), Color.BLUE).setLabel("Available Stock"))
        pieData.add(SliceValue(stockOutQuaintity.toFloat(), Color.GRAY).setLabel("Stock Out"))
        pieData.add(SliceValue(stockInQuaintity.toFloat(), Color.RED).setLabel("Stock In"))
       // pieData.add(SliceValue(60f, Color.MAGENTA).setLabel("Q4: $28"))

        val pieChartData = PieChartData(pieData)
        pieChartData.setHasLabels(true).valueLabelTextSize = 14
        pieChartData.setHasCenterCircle(true).setCenterText1("Stock")
            .setCenterText1FontSize(20).centerText1Color = Color.parseColor("#0097A7")
        pieChartView.pieChartData = pieChartData
        pieChartData.setHasLabels(true)
        pieChartData.setHasLabels(true).valueLabelTextSize = 14

    }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


        // https://www.codingdemos.com/android-pie-chart-tutorial/
/*

        val pieChartView = findViewById<PieChartView>(R.id.chart)

        val pieData: ArrayList<SliceValue> = ArrayList()

        pieData.add(SliceValue(15.toFloat(), Color.BLUE).setLabel("Q1: $10"))
        pieData.add(SliceValue(25.toFloat(), Color.GRAY).setLabel("Q2: $4"))
        pieData.add(SliceValue(10.toFloat(), Color.RED).setLabel("Q3: $18"))
        pieData.add(SliceValue(60.toFloat(), Color.MAGENTA).setLabel("Q4: $28"))


       */
/* val pieChartData: PieChartData = PieChartData((pieData as List<SliceValue>))
        pieChartView.pieChartData = pieChartData
        pieChartData.setHasLabels(true)
        pieChartData.setHasLabels(true).valueLabelTextSize = 14



        chart = findViewById<ComboLineColumnChartView>(R.id.chart_two)
        chart?.onValueTouchListener = ValueTouchListener()

        generateValues()
        generateData()
*//*

        generateValues()
        generateData()
        prepareDataAnimation();
        pieChartView.startDataAnimation();
*/

    }


    private val numberOfLines = 1
    private val maxNumberOfLines = 4
    private val numberOfPoints = 12

    var randomNumbersTab =
        Array(maxNumberOfLines) { FloatArray(numberOfPoints) }

    private val hasAxes = true
    private val hasAxesNames = true
    private val hasPoints = true
    private val hasLines = true
    private val isCubic = false
    private val hasLabels = false

    private fun generateValues() {
        for (i in 0 until maxNumberOfLines) {
            for (j in 0 until numberOfPoints) {
                randomNumbersTab[i][j] = Math.random().toFloat() * 50f + 5
            }
        }
    }

    private fun generateData() {
        // Chart looks the best when line data and column data have similar maximum viewports.
        data = ComboLineColumnChartData(generateColumnData(), generateLineData())
        if (hasAxes) {
            val axisX = Axis()
            val axisY: Axis = Axis().setHasLines(true)
            if (hasAxesNames) {
                axisX.name = "Axis X"
                axisY.name = "Axis Y"
            }
            data?.axisXBottom = axisX
            data?.axisYLeft = axisY
        } else {
            data?.axisXBottom = null
            data?.axisYLeft = null
        }
        chart?.comboLineColumnChartData = data
    }


    private fun generateLineData(): LineChartData? {
        val lines: MutableList<Line> = ArrayList()
        for (i in 0 until numberOfLines) {
            val values: MutableList<PointValue> = ArrayList()
            for (j in 0 until numberOfPoints) {
                values.add(PointValue(j.toFloat(), randomNumbersTab[i][j]))
            }
            val line = Line(values)
            line.color = ChartUtils.COLORS[i]
            line.isCubic = isCubic
            line.setHasLabels(hasLabels)
            line.setHasLines(hasLines)
            line.setHasPoints(hasPoints)
            lines.add(line)
        }
        return LineChartData(lines)
    }

    private fun generateColumnData(): ColumnChartData? {
        val numSubcolumns = 1
        val numColumns = 12
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        val columns: MutableList<Column> = ArrayList()
        var values: MutableList<SubcolumnValue?>
        for (i in 0 until numColumns) {
            values = ArrayList()
            for (j in 0 until numSubcolumns) {
                values.add(
                    SubcolumnValue(
                        Math.random().toFloat() * 50 + 5,
                        ChartUtils.COLOR_GREEN
                    )
                )
            }
            columns.add(Column(values))
        }
        return ColumnChartData(columns)
    }


    private fun prepareDataAnimation() {

        // Line animations
        for (line in data!!.lineChartData.lines) {
            for (value in line.values) {
                // Here I modify target only for Y values but it is OK to modify X targets as well.
                value.setTarget(value.x, Math.random().toFloat() * 50 + 5)
            }
        }

        // Columns animations
        for (column in data!!.columnChartData.columns) {
            for (value in column.values) {
                value.setTarget(Math.random().toFloat() * 50 + 5)
            }
        }
    }


    private inner class ValueTouchListener :
        ComboLineColumnChartOnValueSelectListener {
        override fun onValueDeselected() {
            // TODO Auto-generated method stub
        }

        override fun onColumnValueSelected(
            columnIndex: Int,
            subcolumnIndex: Int,
            value: SubcolumnValue
        ) {
            showToast( "Selected column: $value")
        }

        override fun onPointValueSelected(
            lineIndex: Int,
            pointIndex: Int,
            value: PointValue
        ) {
            showToast("Selected line point: $value")
        }
    }


    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
            .show()
    }
}