package com.example.compose_fundamental_tutorial

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_fundamental_tutorial.ui.theme.Compose_fundamental_tutorialTheme
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_fundamental_tutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                    Container()
//                    CheckBoxContainer()
//                    MySnackbar()
//                    TextFieldTest()
                    NavigationGraph()
                }
            }
        }
    }
}

// ??????????????? ????????? ?????? (?????? ????????? ??????)
enum class NAV_ROUTE(val routeName: String, val description: String, val btnColor: Color){
    MAIN("MAIN", "?????? ??????", Color(0xFF3949AB)),
    LOGIN("LOGIN", "????????? ??????", Color(0xFF5E35B1)),
    REGISTER("REGISTER", "???????????? ??????", Color(0xFFD81B60)),
    USER_PROFILE("USER_PROFILE", "?????? ????????? ??????", Color(0xFF00897B)),
    SETTING("SETTING", "?????? ??????", Color(0xFFF4511E))
}

// ??????????????? ????????? ??????
class RouteAction(navHostController: NavHostController){

    // ?????? ???????????? ??????
    val navTo: (NAV_ROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }

    // ???????????? ??????
    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }
}

@Composable
fun NavigationGraph(startRoute: NAV_ROUTE = NAV_ROUTE.MAIN) {

    // ??????????????? ????????????
    val navController = rememberNavController()

    // ??????????????? ????????? ??????
    val routeAction = remember(navController) { RouteAction(navController) }

    // NavHost ??? ??????????????? ??????
    // ??????????????? ????????? ???????????? ????????????
    NavHost(navController, startRoute.routeName) {

        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.MAIN.routeName){
            // ?????? = ???
            MainScreen(routeAction = routeAction)
        }
        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.LOGIN.routeName){
            // ?????? = ???
            LoginScreen(routeAction = routeAction)
        }
        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.REGISTER.routeName){
            // ?????? = ???
            RegisterScreen(routeAction = routeAction)
        }
        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.USER_PROFILE.routeName){
            // ?????? = ???
            UserProfileScreen(routeAction = routeAction)
        }
        // ????????? ?????? = ????????? ???
        composable(NAV_ROUTE.SETTING.routeName){
            // ?????? = ???
            SettingScreen(routeAction = routeAction)
        }
    }

}

// ?????? ??????
@Composable
fun MainScreen(routeAction: RouteAction){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(16.dp)) {
            NavButton(route = NAV_ROUTE.LOGIN, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.REGISTER, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.USER_PROFILE, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.SETTING, routeAction = routeAction)
        }
    }
}

// ????????? ??????
@Composable
fun LoginScreen(routeAction: RouteAction){
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center){
            Text(text = "????????? ??????", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // ???????????? ??????
            Button(onClick = routeAction.goBack,
                    modifier = Modifier
                        .padding(16.dp)
                        .offset(y = 100.dp)) {
                Text("????????????")
            }
        }
    }
}

// ???????????? ??????
@Composable
fun RegisterScreen(routeAction: RouteAction){
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center){
            Text(text = "???????????? ??????", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // ???????????? ??????
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text("????????????")
            }
        }
    }
}

// ?????? ????????? ??????
@Composable
fun UserProfileScreen(routeAction: RouteAction){
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center){
            Text(text = "?????? ????????? ??????", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // ???????????? ??????
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text("????????????")
            }
        }
    }
}

// ?????? ??????
@Composable
fun SettingScreen(routeAction: RouteAction){
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center){
            Text(text = "?????? ??????", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // ???????????? ??????
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text("????????????")
            }
        }
    }
}

// ????????? ?????? ??????????????? ??????
@Composable
fun ColumnScope.NavButton(route: NAV_ROUTE, routeAction: RouteAction){
    Button(onClick = {
        routeAction.navTo(route)
    },colors = ButtonDefaults.buttonColors(backgroundColor = route.btnColor),
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(text = route.description,
        style = TextStyle(Color.White, 22.sp, FontWeight.Medium)
        )
    }
}



//fun TextField(
//    value: TextFieldValue,
//    onValueChange: (TextFieldValue) -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    readOnly: Boolean = false,
//    textStyle: TextStyle = LocalTextStyle.current,
//    label: @Composable (() -> Unit)? = null,
//    placeholder: @Composable (() -> Unit)? = null,
//    leadingIcon: @Composable (() -> Unit)? = null,
//    trailingIcon: @Composable (() -> Unit)? = null,
//    isError: Boolean = false,
//    visualTransformation: VisualTransformation = VisualTransformation.None,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    keyboardActions: KeyboardActions = KeyboardActions(),
//    singleLine: Boolean = false,
//    maxLines: Int = Int.MAX_VALUE,
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    shape: Shape =
//        MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
//    colors: TextFieldColors = TextFieldDefaults.textFieldColors()
//) {

@Composable
fun TextFieldTest(){

    var userInput by remember { mutableStateOf(TextFieldValue()) }

    var phoneNumberInput by remember { mutableStateOf(TextFieldValue()) }

    var emailInput by remember { mutableStateOf(TextFieldValue()) }

    val shouldShowPassword = remember { mutableStateOf(false) }

    var passwordInput by remember { mutableStateOf(TextFieldValue()) }

    val passwordResource: (Boolean) -> Int = {
        if(it) {
            R.drawable.ic_baseline_visibility_24
        } else {
            R.drawable.ic_baseline_visibility_off_24
        }
    }

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userInput,
            singleLine = false,
            maxLines = 2,
            onValueChange = { newValue -> userInput = newValue },
            label = { Text("????????? ??????") },
            placeholder = { Text("????????? ?????????") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumberInput,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = { newValue -> phoneNumberInput = newValue },
            label = { Text("????????????") },
            placeholder = { Text("010-1234-1234") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
//            trailingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) },
            trailingIcon = { IconButton(onClick = { Log.d("TAG", "TextFieldTest: ???????????? ??????") }) {
                                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
                                }
                           },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { newValue -> emailInput = newValue },
            label = { Text("????????? ??????") },
            placeholder = { Text("????????? ????????? ????????? ?????????") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
//            trailingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) },
            trailingIcon = { IconButton(onClick = {
                Log.d("TAG", "TextFieldTest: ???????????? visible ?????? ??????")
                shouldShowPassword.value = !shouldShowPassword.value
            }) {
                    Icon(painter = painterResource(id = passwordResource(shouldShowPassword.value)), contentDescription = null)
                }
            },
            visualTransformation = if (shouldShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { newValue -> passwordInput = newValue },
            label = { Text("????????????") },
            placeholder = { Text("??????????????? ??????????????????") }
        )
    }
}



@Composable
fun MySnackbar(){

    val snackbarHostState = remember { SnackbarHostState() }

    val coroutineScope = rememberCoroutineScope()

    val buttonTitle : (SnackbarData?) -> String = { snackbarData ->
        if (snackbarData != null) {
            "????????? ?????????"
        } else {
            "????????? ????????????"
        }
    }

    val buttonColor : (SnackbarData?) -> Color = { snackbarData ->
        if (snackbarData != null) {
            Color.Black
        } else {
            Color.Blue
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor(snackbarHostState.currentSnackbarData),
                contentColor = Color.White
            ),
            onClick = {
            Log.d("TAG", "MySnackbar: ????????? ?????? ??????")
            if (snackbarHostState.currentSnackbarData != null) {
                Log.d("TAG", "MySnackbar: ?????? ???????????? ??????.")
                snackbarHostState.currentSnackbarData?.dismiss()
                return@Button
            }
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    "????????? ??????????! ???????????????",
                    "??????",
                    SnackbarDuration.Short
                ).let {
                    when(it) {
                        SnackbarResult.Dismissed -> Log.d("TAG", "MySnackbar: ????????? ?????????")
                        SnackbarResult.ActionPerformed -> Log.d("TAG", "MySnackbar: ????????? ?????? ?????? ??????")
                    }
                }
            } // coroutineScope
        }) {
            Text(buttonTitle(snackbarHostState.currentSnackbarData))
        }

        // ???????????? ???????????? ??????
        SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter))

    }
}


// arrangement ????????? ????????? ????????????
// arrangement ??? Row, Column ?????? ???????????? ????????????
// ????????????????????? ?????????????????? ???????????? ???????????? ????????? ??? ?????? ?????????.
// ??? ?????? css ?????? flex ??? ??????????????? ????????? ?????????.

// horizontal Arranement ????????? Start, End, Center ??? ??????
//Arrangement.SpaceBetween : ?????? ?????? ??????
//Arrangement.Start : ????????????
//Arrangement.End : ???????????????
//Arrangement.SpaceAround : ??? ????????? ????????????
//Arrangement.Center : ???????????? ??????
//Arrangement.SpaceBetween : ????????? ????????? ????????????
//Arrangement.SpaceEvenly : ????????? ????????? ????????? ????????? ??????

// alignment ??? ???????????? ?????? ???????????? ?????? ???????????? ???????????? ????????? ????????? ???????????? ??????????????? ????????????.
// linearLayout ?????? gravity ??? ?????? ????????? ????????? ?????????.
// Alignment.Bottom : ??????????????? ????????? ??????
// Alignment.Top : ??????????????? ?????? ??????
// ????????? Row ???????????? ????????? align??? ???????????? ????????? Center Vertically
// Alignment.CenterVertically : ??????????????? ?????????????????? ????????? ??????

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


// ????????? ????????? ??????.
// ?????? relative layout, constriant layout, framelayout ??? ?????? ??? ???????????? ??????
// ????????? ??????????????? ?????? ?????? ????????? ??????

// alignment??? row, column ?????? ???????????? ??????

// Alignment.BottomCenter : ??????????????? ?????? ??????
// Alignment.BottomEnd : ??????????????? ?????? ?????????
// Alignment.BottomStart : ??????????????? ?????? ??????

// Alignment.Center : ??????????????? ?????????
// Alignment.CenterStart : ??????????????? ?????? ??????
// Alignment.CenterEnd : ??????????????? ?????? ?????????

// Alignment.TopCenter : ??????????????? ??? ??????
// Alignment.TopEnd : ??????????????? ??? ?????????
// Alignment.TopStart : ??????????????? ??? ??????

// propagateMinConstraints ?????? ????????? true??? ??????
// ?????? ?????? ?????? ?????? ?????? ????????? ?????? ???????????? ????????? ?????? ?????? ????????????????????? ?????????.

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
            Text(text = "????????? ??? ????????????")
        } else {
            Text(text = "????????? ?????? ????????????")
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

//onClick: () -> Unit,
//modifier: Modifier = Modifier,
//enabled: Boolean = true,
//interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//elevation: ButtonElevation? = ButtonDefaults.elevation(),
//shape: Shape = MaterialTheme.shapes.small,
//border: BorderStroke? = null,
//colors: ButtonColors = ButtonDefaults.buttonColors(),
//contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
//content: @Composable RowScope.() -> Unit

// Button
// enable: ???????????? ??????
// interactionSource: ???????????? ???????????? ??????
// elevation: ????????? ??? ????????? ?????? ???????????? ????????? ?????????
// ????????? ????????? ?????? ???
// shape: ??????
// border: ?????????
// colors: ?????? ???
// contentPadding: ????????? ???????????? ??????

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ButtonsContainer(){

    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Yellow, Color.Red))

    val interactionSource = remember { MutableInteractionSource() }

    val isPressed by interactionSource.collectIsPressedAsState()

    val pressStatusTitle = if (isPressed) "????????? ????????? ??????." else "???????????? ?????? ??????."

    val interactionSourceForSecondBtn = remember { MutableInteractionSource() }

    val isPressedForSecondBtn by interactionSourceForSecondBtn.collectIsPressedAsState()

    val pressedBtnRadius = if (isPressedForSecondBtn) 0.dp else 20.dp

    val pressedBtnRadiusWithAnim: Dp by animateDpAsState(
        if (isPressedForSecondBtn) 0.dp else 20.dp
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            enabled = true,
            onClick = {
            Log.d("TAG", "ButtonsContainer: ?????? 1 ??????")
        }) {
            Text(text = "?????? 1")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 2 ??????")
            }) {
            Text(text = "?????? 2")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = CircleShape,
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 3 ??????")
            }) {
            Text(text = "?????? 3")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, Color.Red),
            contentPadding = PaddingValues(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 4 ??????")
            }) {
            Text(text = "?????? 4")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.LightGray
            ),
            interactionSource = interactionSource,
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 5 ??????")
            }) {
            Text(text = "?????? 5", color = Color.White)
        }

//        if (isPressed) {
//            Text(text = "????????? ????????? ??????.")
//        } else {
//            Text(text = "???????????? ?????? ??????.")
//        }

        Text(text = "$pressStatusTitle")

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.LightGray
            ),
            interactionSource = interactionSourceForSecondBtn,
            modifier = Modifier.drawColoredShadow(
                color = Color.Blue,
                alpha = 0.5f,
                borderRadius = 10.dp,
                shadowRadius = pressedBtnRadiusWithAnim,
                offsetY = 0.dp,
                offsetX = 0.dp,
            ),
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 5 ??????")
            }) {
            Text(text = "?????? 5", color = Color.White)
        }

//        color: Color,
//        alpha: Float = 0.2f,
//        borderRadius: Dp = 0.dp,
//        shadowRadius: Dp = 20.dp,
//        offsetY: Dp = 0.dp,
//        offsetX: Dp = 0.dp

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
    val name = "?????????"

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
        Text(text = "???????????????? $name",
            style = TextStyle(
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = "????????????????  $name",
            style = TextStyle(
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = "????????????????  $name",
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
            append("????????????????")

            withStyle(style = SpanStyle(color = Color.Blue,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold
            )
            ) {
                append("???????????? ????????? ?????????!")
            }
            withStyle(style = SpanStyle(color = Color.Red)
            ) {
                append("???!??????")
            }
        })

        Text(text = buildAnnotatedString {
            wordsArray.forEach{
                if (it.contains("??????")){
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

        ClickableText(text = AnnotatedString("?????????!"), onClick = {
            Log.d("TAG", "TextContainer: ???????????? ???????????????!")
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
    // color ??? ?????? ????????? ?????? ?????? ???????????? ?????? ????????? ?????? ?????? ????????????
    val randomColor = color?.let { it } ?: Color(red, green, blue)
    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor))
}

@Composable
fun ShapeContainer(){

    var polySides by remember { mutableStateOf(3) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        DummyBox(modifier = Modifier.clip(RectangleShape))
        DummyBox(modifier = Modifier.clip(CircleShape))
        DummyBox(modifier = Modifier.clip(RoundedCornerShape(20.dp)))
        DummyBox(modifier = Modifier.clip(CutCornerShape(20.dp)))
        DummyBox(modifier = Modifier.clip(TriangleShape()))
        DummyBox(modifier = Modifier.clip(PolyShape(polySides, 100f)))

        Text(text = "polySides: $polySides")
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                polySides = polySides + 1
            }) {
                Text(text = "polySides + 1")
            }
            Button(onClick = {
                polySides = 3
            }) {
                Text(text = "?????????")
            }
        }

    }
}

class TriangleShape(): Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path = path)
    }
}

class PolyShape(private val sides: Int, private val radius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply { this.polygon(sides, radius, size.center) })
    }
}


fun Path.polygon(sides: Int, radius: Float, center: Offset) {
    val angle = 2.0 * Math.PI / sides
    moveTo(
        x = center.x + (radius * cos(0.0)).toFloat(),
        y = center.y + (radius * sin(0.0)).toFloat()
    )
    for (i in 1 until sides) {
        lineTo(
            x = center.x + (radius * cos(angle * i)).toFloat(),
            y = center.y + (radius * sin(angle * i)).toFloat()
        )
    }
    close()
}

//checked: Boolean,
//onCheckedChange: ((Boolean) -> Unit)?,
//modifier: Modifier = Modifier,
//enabled: Boolean = true,
//interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//colors: CheckboxColors = CheckboxDefaults.colors()

// checked : ?????? ??????
// onCheckedChange: ?????? ?????? ?????? ?????? ?????????
// enabled: ?????? ?????? ??????
// colors: ?????? ????????? ?????? ??? ??????

//????????????????????`MutableState`??????????? ???????????? ?????? ??? ?????? ????????? ????????????.
//
//- `val mutableState = remember { mutableStateOf(default) }`
//- `var value by remember { mutableStateOf(default) }`
//- `val (value, setValue) = remember { mutableStateOf(default) }`

@Composable
fun CheckBoxContainer(){

    val checkedStatusForFirst = remember { mutableStateOf(false) }
    val checkedStatusForSecond = remember { mutableStateOf(false) }
    val checkedStatusForThird = remember { mutableStateOf(false) }
//    val checkedStatusForForth = remember { mutableStateOf(false) }

    val checkedStatesArray = listOf(
                                checkedStatusForFirst,
                                checkedStatusForSecond,
                                checkedStatusForThird,
                            )

    val allBoxChecked: (Boolean) -> Unit = { isAllBoxChecked ->
        Log.d("TAG", "CheckBoxContainer: isAllBoxChecked : $isAllBoxChecked")
        checkedStatesArray.forEach { it.value = isAllBoxChecked }
    }

//    val checkedStatusForForth : Boolean = checkedStatesArray.all { it.value == true }
    val checkedStatusForForth : Boolean = checkedStatesArray.all { it.value }

//    var checkedStatusForSecond by remember { mutableStateOf(false) }
//
//    var (checkedStatusForThird, setCheckedStatusForThird) = remember { mutableStateOf(false) }

    var (checkedStatusForFourth, setCheckedStatusForFourth) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CheckBoxWithTitle("1??? ????????????", checkedStatusForFirst)
        CheckBoxWithTitle("2??? ????????????", checkedStatusForSecond)
        CheckBoxWithTitle("3??? ????????????", checkedStatusForThird)

//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForSecond,
//            onCheckedChange = { isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                checkedStatusForSecond = isChecked
//            })
//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForThird,
//            onCheckedChange = {
//                Log.d("TAG", "CheckBoxContainer: isChecked: $it")
//                setCheckedStatusForThird.invoke(it)
//            })
        Spacer(modifier = Modifier.height(10.dp))
        AllAgreeCheckBox("?????? ???????????????????", checkedStatusForForth, allBoxChecked)
        Spacer(modifier = Modifier.height(10.dp))
        MyCustomCheckBox(title = "????????? ???????????? ?????? O", withRipple = true)
        MyCustomCheckBox(title = "????????? ???????????? ?????? X", withRipple = false)
//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForFourth,
//            colors = CheckboxDefaults.colors(
//                checkedColor = Color.Red,
//                uncheckedColor = Color(0xFFEF9A9A),
//                checkmarkColor = Color.Black,
//                disabledColor = Color(0xFF90CAF9)
//            ),
//            onCheckedChange = {
//                Log.d("TAG", "CheckBoxContainer: isChecked: $it")
//                setCheckedStatusForFourth.invoke(it)
//            })
    }
}

@Composable
fun CheckBoxWithTitle(title: String, isCheckedState: MutableState<Boolean>) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(
            enabled = true,
            checked = isCheckedState.value,
            onCheckedChange = { isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
                isCheckedState.value = isChecked
            })
        Text(text = title)
    }
}

@Composable
fun AllAgreeCheckBox(title: String,
                     shouldChecked: Boolean ,
                     allBoxChecked: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(
            enabled = true,
            checked = shouldChecked,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color(0xFFEF9A9A),
                checkmarkColor = Color.White,
                disabledColor = Color(0xFF90CAF9)
            ),
            onCheckedChange = { isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                isCheckedState.value = isChecked
                allBoxChecked(isChecked)
            })
        Text(text = title)
    }
}

@Composable
fun MyCustomCheckBox(title: String, withRipple: Boolean = false){

//    var isCheckedState by remember { mutableStateOf(false) }
//    var isChecked = remember { mutableStateOf(false) }
    var (isChecked, setIsChecked) = remember { mutableStateOf(false) }

    var togglePainter = if (isChecked == true) R.drawable.ic_checked else R.drawable.ic_unchecked

    var checkedInfoString = if (isChecked) "?????????" else "????????????"

    var rippleEffect = if (withRipple) rememberRipple(
        radius = 30.dp,
        bounded = false,
        color = Color.Blue
    ) else null

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
//            .background(Color.Yellow)
                .clickable(
                    indication = rippleEffect,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    setIsChecked.invoke(!isChecked)
                    Log.d("TAG", "MyCustomCheckBox: ????????? ?????????! / $isChecked")
                }){
            Image(
                painter = painterResource(id = togglePainter),
                contentDescription = null
            )
        }
        Text(text = "$title / $checkedInfoString")
    }
}

//bounded: Boolean = true,
//radius: Dp = Dp.Unspecified,
//color: Color = Color.Unspecified


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_fundamental_tutorialTheme {
        CheckBoxContainer()
//        Container()
//        Greeting("Android")
    }
}
