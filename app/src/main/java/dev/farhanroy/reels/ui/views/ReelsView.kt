package dev.farhanroy.reels.ui.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import dev.farhanroy.reels.ui.components.VideoPlayer
import dev.farhanroy.reels.util.DummyData
import dev.farhanroy.reels.util.Reel
import dev.farhanroy.reels.R

val verticalPadding = 12.dp
val horizontalPadding = 10.dp


@Composable
fun ReelsView() {
    Box(modifier = Modifier.background(color = Color.Black)) {
        ReelsList()
        ReelsHeader()
    }
}

@Composable
private fun ReelsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Reels", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 21.sp)
        Icon(
            ImageBitmap.imageResource(id = R.drawable.ic_outlined_camera),
            tint = Color.White,
            modifier = Modifier.size(24.dp),
            contentDescription = ""
        )
    }
}

@Composable
private fun ReelsList() {
    val reels = DummyData.reels

/*
  val configuration = ConfigurationAmbient.current
  val reelsHeight = configuration.screenHeightDp.dp - bottomBarHeight
*/

    LazyColumn{

        items(reels.size) { index ->
            Box(
                modifier = Modifier.fillParentMaxSize(),
            ) {

                VideoPlayer(uri = reels[index].getVideoUrl())

                Column(
                    modifier = Modifier.align(Alignment.BottomStart),
                ) {
                    ReelFooter(reel = reels[index])
                    Divider()
                }
            }
        }
    }
}
@Composable
private fun ReelFooter(
    reel: Reel
) {
    Row(modifier = Modifier.fillMaxWidth().padding(start = 18.dp, bottom = 18.dp), verticalAlignment = Alignment.Bottom) {
        FooterUserData(reel = reel, modifier = Modifier.weight(8f))
        FooterUserAction(reel = reel, modifier = Modifier.weight(2f))
    }
}

@Composable
private fun FooterUserData(reel: Reel, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {

        // user data
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                imageModel = reel.userImage,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(28.dp)
                    .background(color = Color.Gray, shape = CircleShape)
                    .clip(CircleShape),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(horizontalPadding))
            Text(
                text = reel.userName,
                color = Color.White,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.width(horizontalPadding))
            Canvas(modifier = Modifier.size(5.dp), onDraw = {
                drawCircle(
                    color = Color.White,
                    radius = 8f
                )
            })
            Spacer(modifier = Modifier.width(horizontalPadding))
            Text(
                text = "Follow",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2
            )


        }

        Spacer(modifier = Modifier.height(horizontalPadding))

        // comment
        Text(text = "wkwkwkwk... 😄", color = Color.White)

        Spacer(modifier = Modifier.height(horizontalPadding))

        // Audio
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(reel.userName, color = Color.White)
            Spacer(modifier = Modifier.width(horizontalPadding))
            Canvas(modifier = Modifier.size(5.dp), onDraw = {
                drawCircle(
                    color = Color.White,
                    radius = 8f
                )
            })
            Spacer(modifier = Modifier.width(horizontalPadding))
            Text(
                text = "Audio asli",
                color = Color.White
            )
        }


    }
}

@Composable
private fun FooterUserAction(reel: Reel, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        UserActionWithText(
            drawableRes = R.drawable.ic_outlined_favorite,
            text = reel.likesCount.toString()
        )
        Spacer(modifier = Modifier.height(28.dp))
        UserActionWithText(
            drawableRes = R.drawable.ic_outlined_comment,
            text = reel.commentsCount.toString()
        )
        Spacer(modifier = Modifier.height(28.dp))
        UserAction(R.drawable.ic_dm)
        Spacer(modifier = Modifier.height(28.dp))
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.height(28.dp))
        GlideImage( imageModel = reel.userImage,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(28.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(6.dp))
                .clip(RoundedCornerShape(6.dp)),
            contentDescription = null)

    }
}

@Composable
private fun UserAction(@DrawableRes drawableRes: Int) {
    Icon(
        ImageBitmap.imageResource(id = drawableRes),
        tint = Color.White,
        modifier = Modifier.size(26.dp),
        contentDescription = ""
    )
}

@Composable
private fun UserActionWithText(
    @DrawableRes drawableRes: Int,
    text: String
) {
    Icon(
        ImageBitmap.imageResource(id = drawableRes),
        tint = Color.White,
        modifier = Modifier.size(28.dp),
        contentDescription = ""
    )
    Spacer(modifier = Modifier.height(6 .dp))
    Text(
        text = text,
        color = Color.White,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
private fun LoadingIndicator(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Gray,
            strokeWidth = 2.dp
        )
    }
}