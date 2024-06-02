package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var clear = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 0))
    var puzzle = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 0), arrayOf(7, 8, 6))
    var row = 0
    var col = 0
    var temp = 0
    var gameCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val puzzle1 = puzzle.flatten().toTypedArray()
        puzzle1.shuffle()

        val rows = puzzle.size
        val cols = puzzle[0].size
        puzzle = Array(rows) { row -> Array(cols) { col -> puzzle1[row * cols + col] } }

        psetting()

        binding.p1.setOnClickListener {
            row = 0
            col = 0
            pChange()
        }
        binding.p2.setOnClickListener {
            row = 0
            col = 1
            pChange()
        }
        binding.p3.setOnClickListener {
            row = 0
            col = 2
            pChange()
        }
        binding.p4.setOnClickListener {
            row = 1
            col = 0
            pChange()
        }
        binding.p5.setOnClickListener {
            row = 1
            col = 1
            pChange()
        }
        binding.p6.setOnClickListener {
            row = 1
            col = 2
            pChange()
        }
        binding.p7.setOnClickListener {
            row = 2
            col = 0
            pChange()
        }
        binding.p8.setOnClickListener {
            row = 2
            col = 1
            pChange()
        }
        binding.p9.setOnClickListener {
            row = 2
            col = 2
            pChange()
        }

        binding.Mix.setOnClickListener {
            mixPuzzle()
        }
    }

    fun psetting() {
        setImageView(binding.p1, puzzle[0][0])
        setImageView(binding.p2, puzzle[0][1])
        setImageView(binding.p3, puzzle[0][2])
        setImageView(binding.p4, puzzle[1][0])
        setImageView(binding.p5, puzzle[1][1])
        setImageView(binding.p6, puzzle[1][2])
        setImageView(binding.p7, puzzle[2][0])
        setImageView(binding.p8, puzzle[2][1])
        setImageView(binding.p9, puzzle[2][2])
    }

    fun setImageView(imageView: ImageView, value: Int) {
        val resId = when(value) {
            1 -> R.drawable.p1
            2 -> R.drawable.p2
            3 -> R.drawable.p3
            4 -> R.drawable.p4
            5 -> R.drawable.p5
            6 -> R.drawable.p6
            7 -> R.drawable.p7
            8 -> R.drawable.p8
            0 -> R.drawable.p9
            else -> R.drawable.p9
        }
        imageView.setImageResource(resId)
    }

    fun pClear() {
        if (clear.contentDeepEquals(puzzle)) {
            binding.p1.setImageResource(R.drawable.p1)
            binding.p2.setImageResource(R.drawable.p2)
            binding.p3.setImageResource(R.drawable.p3)
            binding.p4.setImageResource(R.drawable.p4)
            binding.p5.setImageResource(R.drawable.p5)
            binding.p6.setImageResource(R.drawable.p6)
            binding.p7.setImageResource(R.drawable.p7)
            binding.p8.setImageResource(R.drawable.p8)
            binding.p9.setImageResource(R.drawable.p9)
            gameCompleted = true
        }
    }

    fun pChange() {
        if (gameCompleted) return

        val numRows = puzzle.size
        val numCols = puzzle[0].size

        if (row > 0 && puzzle[row - 1][col] == 0) { // 위
            temp = puzzle[row][col]
            puzzle[row][col] = puzzle[row - 1][col]
            puzzle[row - 1][col] = temp
            psetting()
        } else if (row < numRows - 1 && puzzle[row + 1][col] == 0) { // 아래
            temp = puzzle[row][col]
            puzzle[row][col] = puzzle[row + 1][col]
            puzzle[row + 1][col] = temp
            psetting()
        } else if (col > 0 && puzzle[row][col - 1] == 0) { // 왼쪽
            temp = puzzle[row][col]
            puzzle[row][col] = puzzle[row][col - 1]
            puzzle[row][col - 1] = temp
            psetting()
        } else if (col < numCols - 1 && puzzle[row][col + 1] == 0) { // 오른쪽
            temp = puzzle[row][col]
            puzzle[row][col] = puzzle[row][col + 1]
            puzzle[row][col + 1] = temp
            psetting()
        }
        if (isPuzzleCompleted()) {
            pClear()
        }
    }

    fun mixPuzzle() {
        val puzzle1 = puzzle.flatten().toTypedArray()
        puzzle1.shuffle()

        val rows = puzzle.size
        val cols = puzzle[0].size
        puzzle = Array(rows) { row -> Array(cols) { col -> puzzle1[row * cols + col] } }

        gameCompleted = false
        psetting()
    }

    fun isPuzzleCompleted(): Boolean {
        return clear.contentDeepEquals(puzzle)
    }
}
