package com.example.dogagecalculator.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dogagecalculator.R
import kotlinx.coroutines.launch

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
                    style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(
                        R.color.third)
                    )
                )
                Text(text = stringResource(R.string.developer_name),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                Text(text = stringResource(R.string.course_title),
                    style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(
                        R.color.third)
                    )
                )
                Text(text = stringResource(R.string.course_name),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
                Text(text = stringResource(R.string.about_title),
                    style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(
                        R.color.third)
                    )
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
                Text(text = stringResource(R.string.functionality_title), style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(
                    R.color.third)
                )
                )
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
                Text(text = stringResource(R.string.disclaimer_title), style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = colorResource(
                    R.color.third)
                )
                )
                Text(text = stringResource(R.string.disclaimer_content),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
            }
        },
    )
}
