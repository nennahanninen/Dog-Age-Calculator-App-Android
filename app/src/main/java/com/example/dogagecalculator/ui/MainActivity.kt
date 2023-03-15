package com.example.dogagecalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.dogagecalculator.R
import com.example.dogagecalculator.model.FactApi
import com.example.dogagecalculator.ui.theme.AppTheme
import com.example.dogagecalculator.ui.theme.Second
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}

@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = stringResource(R.string.home)
    ) {
        composable(route = "Home" ) {
            MainScreen(navController)
        }
        composable(route = "Info" ) {
            InfoScreen(navController)
        }
        composable(route = "Chart" ) {
            ChartScreen(navController)
        }
    }
}

@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar (
        title = { Text(title) },
        actions = {
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                DropdownMenuItem(onClick = { navController.navigate("info") }) {
                    Text(text = stringResource(R.string.info))
                }
                DropdownMenuItem(onClick = { navController.navigate("chart") }) {
                    Text(text = stringResource(R.string.chart))
                }
            }
        }
    )
}

@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar (
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    var ageInput by remember { mutableStateOf("") }
    val age = ageInput.toIntOrNull() ?: 0
    var size by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0) }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch {
            scrollState.scrollTo(0)
        }
    }

    Scaffold (
        topBar = { MainTopBar(title = stringResource(R.string.app_name), navController )},
        content = { Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(state = scrollState)
                ){
            Divider()
            Text(text = stringResource(R.string.app_title), textAlign = TextAlign.Center, style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = colorResource(R.color.third)))
            AgeField(ageInput = ageInput, onValueChange = { ageInput = it })
            DogSizeList{ size = it }
            Text(text = stringResource(R.string.result_text), style = TextStyle(fontSize = 18.sp, color = colorResource(R.color.third)))
            Text(text = "$result", style = TextStyle(fontSize = 70.sp, color = Second))
            Calculation(age = age, size = size, setResult = { result = it})
            Divider()
            Text(text = stringResource(R.string.random_dog_fact), style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = colorResource(R.color.third)))
            FactScreen()
            }
        },
    )
}

@Composable
fun Calculation(age: Int, size: String, setResult:(Int) -> Unit) {
    val small = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    val medium = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    val large = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    val giant = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
    var result = 0
    Button(
        onClick = {
            if (size == "Small" && age in small) {
                result = when (age) {
                    1 -> 15
                    2 -> 24
                    3 -> 28
                    4 -> 32
                    5 -> 36
                    6 -> 40
                    7 -> 44
                    8 -> 48
                    9 -> 52
                    10 -> 56
                    11 -> 60
                    12 -> 64
                    13 -> 68
                    14 -> 72
                    15 -> 76
                    16 -> 80
                    else -> 0
                }
            } else if (size == "Medium" && age in medium) {
                result = when (age) {
                    1 -> 15
                    2 -> 24
                    3 -> 28
                    4 -> 32
                    5 -> 36
                    6 -> 42
                    7 -> 47
                    8 -> 51
                    9 -> 56
                    10 -> 60
                    11 -> 65
                    12 -> 69
                    13 -> 74
                    14 -> 78
                    15 -> 83
                    16 -> 87
                    else -> 0
                }
            } else if (size == "Large" && age in large) {
                result = when (age) {
                    1 -> 15
                    2 -> 24
                    3 -> 28
                    4 -> 32
                    5 -> 36
                    6 -> 45
                    7 -> 50
                    8 -> 55
                    9 -> 61
                    10 -> 66
                    11 -> 72
                    12 -> 77
                    13 -> 82
                    14 -> 88
                    15 -> 93
                    16 -> 99
                    else -> 0
                }
            } else if (size == "Giant" && age in giant) {
                result = when (age) {
                    1 -> 15
                    2 -> 22
                    3 -> 31
                    4 -> 38
                    5 -> 45
                    6 -> 49
                    7 -> 56
                    8 -> 64
                    9 -> 71
                    10 -> 79
                    11 -> 86
                    12 -> 93
                    13 -> 100
                    14 -> 107
                    15 -> 114
                    16 -> 121
                    else -> 0
                }
            }
            setResult(result)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.get_result))
    }
}


@Composable
fun AgeField(ageInput: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = ageInput,
        onValueChange = onValueChange,
        label = {Text(text = stringResource(R.string.enter_dog_age))},
        singleLine= true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun FactScreen() {
    var fact by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        val factResponse = withContext(Dispatchers.IO) { FactApi.instance.getFact() }
        fact = factResponse.fact
        isLoading = false
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)) {
        if (isLoading) {
            CircularProgressIndicator()
            Text(text = stringResource(R.string.fact_loading_text))
        } else {
            Text(
                text = fact,
                style = typography.body1, textAlign = TextAlign.Center
            )
        }
    }
}



@Composable
fun DogSizeList(onClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf( Size.Zero ) }
    val items = listOf("Small", "Medium", "Large", "Giant")

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            readOnly = true,
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = {Text(text = stringResource(R.string.select_dog_size))},
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            items.forEach{ label ->
                DropdownMenuItem(onClick = {
                    selectedText = label

                    val dogSizeSelected: String = when (label) {
                        "Small" -> "Small"
                        "Medium" -> "Medium"
                        "Large" -> "Large"
                        "Giant" -> "Giant"
                        else -> "Medium"
                    }
                    onClick(dogSizeSelected)
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
fun InfoScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch {
            scrollState.scrollTo(0)
        }
    }

    // Creating an annotated string
    val annotatedLinkString1 = buildAnnotatedString {

        // Creating a string to display in the Text
        val mStr1 = stringResource(R.string.description_1_link_text)

        // Word and span to be hyperlinked
        val mStartIndex = mStr1.indexOf("link")
        val mEndIndex = mStartIndex + 4

        append(mStr1)
        addStyle(
            style = SpanStyle(
                color = colorResource(R.color.third),
                textDecoration = TextDecoration.Underline
            ), start = mStartIndex, end = mEndIndex
        )

        // Attach a string annotation that stores the URL to the text "link"
        addStringAnnotation(
            tag = "URL1",
            annotation = stringResource(R.string.hyper_link_1),
            start = mStartIndex,
            end = mEndIndex
        )
    }
    // Creating an annotated string
    val annotatedLinkString2 = buildAnnotatedString {

        // Creating a string to display in the Text
        val mStr2 = stringResource(R.string.description_2_link_text)

        // Word and span to be hyperlinked
        val mStartIndex = mStr2.indexOf("here")
        val mEndIndex = mStartIndex + 4

        append(mStr2)
        addStyle(
            style = SpanStyle(
                color = colorResource(R.color.third),
                textDecoration = TextDecoration.Underline
            ), start = mStartIndex, end = mEndIndex
        )

        // Attach a string annotation that stores the URL to the text "link"
        addStringAnnotation(
            tag = "URL2",
            annotation = stringResource(R.string.hyper_link_2),
            start = mStartIndex,
            end = mEndIndex
        )
    }
    // UriHandler parse and opens URI inside AnnotatedString Item in Browse
    val mUriHandler = LocalUriHandler.current

    Scaffold (
        topBar = { ScreenTopBar(title = stringResource(R.string.info), navController )},
        content = {
            Column (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(state = scrollState)
                    ){
                Text(text = stringResource(R.string.developer_title),
                    style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(R.color.third))
                )
                Text(text = stringResource(R.string.developer_name),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                Text(text = stringResource(R.string.course_title),
                    style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(R.color.third))
                )
                Text(text = stringResource(R.string.course_name),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                Text(text = stringResource(R.string.about_title),
                    style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(R.color.third))
                )
                Text(
                    text = stringResource(R.string.description_1),
                    style = TextStyle(textAlign = TextAlign.Center)
                    )
                ClickableText(
                    style = TextStyle(textAlign = TextAlign.Center),
                    text = annotatedLinkString1,
                    onClick = {
                        annotatedLinkString1
                            .getStringAnnotations("URL1", it, it)
                            .firstOrNull()?.let { stringAnnotation ->
                                mUriHandler.openUri(stringAnnotation.item)
                            }
                    }
                )
                Text(text = stringResource(R.string.description_2),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                ClickableText(
                    style = TextStyle(textAlign = TextAlign.Center),
                    text = annotatedLinkString2,
                    onClick = {
                        annotatedLinkString2
                            .getStringAnnotations("URL2", it, it)
                            .firstOrNull()?.let { stringAnnotation ->
                                mUriHandler.openUri(stringAnnotation.item)
                            }
                    }
                )
                Text(text = stringResource(R.string.functionality_title), style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(R.color.third)))
                Text(text = stringResource(R.string.functionality_content_1),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                Text(text = stringResource(R.string.functionality_content_2),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                Text(text = stringResource(R.string.functionality_content_3),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                Text(text = stringResource(R.string.functionality_content_4),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                Text(text = stringResource(R.string.disclaimer_title), style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(R.color.third)))
                Text(text = stringResource(R.string.disclaimer_content),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                }
                  },
    )
}

@Composable
fun ChartScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch {
            scrollState.scrollTo(0)
        }
    }

    Scaffold (
        topBar = { ScreenTopBar(title = stringResource(R.string.chart), navController )},
        content = { Column (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(state = scrollState)
        ){
            AsyncImage(
                model = stringResource(R.string.chart_image_url),
                contentDescription = stringResource(R.string.chart_description)
            )
        }},
    )
}
