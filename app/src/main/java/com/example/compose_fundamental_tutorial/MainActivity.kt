package com.example.compose_fundamental_tutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_fundamental_tutorial.ui.theme.Compose_fundamental_tutorialTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_fundamental_tutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                    Container()
                    TextContainer()
                }
            }
        }
    }
}

// arrangement 요소를 어떤식 배열할지
// arrangement 는 Row, Column 같은 요소들이 들어가는
// 컨테이너성격의 콤포저블에서 요소들의 아이템을 정렬할 때 사용 됩니다.
// 웹 개발 css 에서 flex 와 비슷하다고 보시면 됩니다.

// horizontal Arranement 이니까 Start, End, Center 만 존재
//Arrangement.SpaceBetween : 공간 모두 차지
//Arrangement.Start : 왼쪽으로
//Arrangement.End : 오른쪽으로
//Arrangement.SpaceAround : 빈 공간을 남겨두기
//Arrangement.Center : 요소들에 넣기
//Arrangement.SpaceBetween : 사이에 공간을 밀어넣기
//Arrangement.SpaceEvenly : 요소들 사이에 공간을 똑같이 하기

// alignment 는 말그대로 해당 컨테이너 안에 들어있는 요소들의 위치를 어떠한 방향으로 정렬할지를 정합니다.
// linearLayout 에서 gravity 와 동일 하다고 보시면 됩니다.
// Alignment.Bottom : 컨테이너의 아래에 두기
// Alignment.Top : 컨테이너의 위에 두기
// 현재는 Row 콤포저블 안에서 align이 들어가기 때문에 Center Vertically
// Alignment.CenterVertically : 컨테이너의 수직방향으로 중앙에 두기

@Composable
fun Container(){
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}


// 박스는 겹칠수 있다.
// 기존 relative layout, constriant layout, framelayout 과 같이 뷰 겹치기가 가능
// 아래로 내려갈수록 위에 뷰를 올리는 방식

// alignment는 row, column 보다 다양하게 지원

// Alignment.BottomCenter : 컨테이너의 중앙 아래
// Alignment.BottomEnd : 컨테이너의 아래 오른쪽
// Alignment.BottomStart : 컨테이너의 아래 왼쪽

// Alignment.Center : 컨테이너의 정중앙
// Alignment.CenterStart : 컨테이너의 중앙 왼쪽
// Alignment.CenterEnd : 컨테이너의 중앙 오른쪽

// Alignment.TopCenter : 컨테이너의 위 중앙
// Alignment.TopEnd : 컨테이너의 위 오른쪽
// Alignment.TopStart : 컨테이너의 위 왼쪽

// propagateMinConstraints 해당 옵션을 true로 하면
// 박스 안에 있는 제일 작은 크기의 뷰를 컨테이너 박스의 크기 만큼 컨스트레인트를 겁니다.

@Composable
fun BoxContainer(){
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        DummyBox(modifier = Modifier.size(150.dp), color = Color.Yellow)
        DummyBox(color = Color.Blue)
    }
}

@Composable
fun BoxWithConstraintContainer(){
    BoxWithConstraints(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {

        if (this.minHeight > 400.dp){
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
        } else {
            DummyBox(modifier = Modifier.size(200.dp), color = Color.Yellow)
        }
        Text(text = "minHeight: ${this.minHeight}")
//        Column() {
//            BoxWithConstraintItem(modifier = Modifier
//                .size(200.dp)
//                .background(Color.Yellow)
//            )
//            BoxWithConstraintItem(modifier = Modifier
//                .size(300.dp)
//                .background(Color.Green)
//            )
//        }

//        DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
//        DummyBox(modifier = Modifier.size(150.dp), color = Color.Yellow)
//        DummyBox(color = Color.Blue)
    }
}

@Composable
fun BoxWithConstraintItem(modifier: Modifier = Modifier){
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        if (this.minWidth > 200.dp) {
            Text(text = "이것은 큰 상자이다")
        } else {
            Text(text = "이것은 작은 상자이다")
        }
    }
}

@Composable
fun VerticalContainer(){
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}

//text: String,
//modifier: Modifier = Modifier,
//color: Color = Color.Unspecified,
//fontSize: TextUnit = TextUnit.Unspecified,
//fontStyle: FontStyle? = null,
//fontWeight: FontWeight? = null,
//fontFamily: FontFamily? = null,
//letterSpacing: TextUnit = TextUnit.Unspecified,
//textDecoration: TextDecoration? = null,
//textAlign: TextAlign? = null,
//lineHeight: TextUnit = TextUnit.Unspecified,
//overflow: TextOverflow = TextOverflow.Clip,
//softWrap: Boolean = true,
//maxLines: Int = Int.MAX_VALUE,
//onTextLayout: (TextLayoutResult) -> Unit = {},
//style: TextStyle = LocalTextStyle.current

@Composable
fun TextContainer() {
    val name = "쩡대리"

    val scrollState = rememberScrollState()

    var words = stringResource(id = R.string.dummy_short_text)
    var wordsArray = words.split(" ")


    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState)
    ) {
        Text(text = "안녕하세요? $name",
            style = TextStyle(
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = "안녕하세요?  $name",
            style = TextStyle(
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = "안녕하세요?  $name",
            style = TextStyle(
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = stringResource(id = R.string.dummy_short_text),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                textAlign = TextAlign.Justify,
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough,
                        TextDecoration.Underline,
                    )
                )
            ),
            fontWeight = FontWeight.W200,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = stringResource(id = R.string.dummy_short_text),
            style = TextStyle(
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.cafe24, weight = FontWeight.ExtraBold)),
                lineHeight = 40.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = buildAnnotatedString {
            append("안녕하세요?")

            withStyle(style = SpanStyle(color = Color.Blue,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold
            )
            ) {
                append("개발하는 정대리 입니다!")
            }
            withStyle(style = SpanStyle(color = Color.Red)
            ) {
                append("빡!코딩")
            }
        })

        Text(text = buildAnnotatedString {
            wordsArray.forEach{
                if (it.contains("심장")){
                    withStyle(style = SpanStyle(color = Color.Blue,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    ) {
                        append("$it ")
                    }
                } else {
                    append("$it ")
                }
            }
        })

        ClickableText(text = AnnotatedString("클릭미!"), onClick = {
            Log.d("TAG", "TextContainer: 클릭미가 클릭되었다!")
        })

        Text(text = stringResource(id = R.string.dummy_long_text),
            style = TextStyle(lineHeight = 20.sp)
        )

    }
}



@Composable
fun DummyBox(modifier: Modifier = Modifier, color: Color? = null){
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    // color 가 값이 있으면 해당 값을 넣어주고 값이 없다면 랜덤 값을 넣어주기
    val randomColor = color?.let { it } ?: Color(red, green, blue)
    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor))
}


//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_fundamental_tutorialTheme {
        TextContainer()
//        Container()
//        Greeting("Android")
    }
}
