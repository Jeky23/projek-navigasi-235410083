/*TODO 0: Program ini diambil dari codelab yang saya beri sedikit modifikasi
   dan penjelasan yang saya pahami dari developer android di modul */

package com.example.cupcake.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.data.DataSource

/**
 * Composable that allows the user to select the desired cupcake quantity and expects
 * [onNextButtonClicked] lambda that expects the selected quantity and triggers the navigation to
 * next screen
 */


@Composable
fun StartOrderScreen(

    /*TODO 1: parameter quantityOptions yang memuat daftar Pair<Int, Int>.
    *  Pair adalah sepasang nilai, menggunakan dua parameter jenis generik yaitu int */
    quantityOptions: List<Pair<Int, Int>>,

    /*TODO 2:parameter bernama onNextButtonClicked dari jenis () -> Unit untuk pengendali button
    *  button dalam hal ini adalah 'Pesan satu', 'Pesan Sepuluh', dan 'Pesan Duapuluh'. */
    onNextButtonClicked: (Int) -> Unit,

    /*TODO 3: parameter modivier*/
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        /*TODO 4: Fungsi kolom yang memuat gambar, teks dan teksbutton */
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,

            //parameter vertikal dengan jarak 8 dp, jarak ini dipanggil dari file dimens.xml di paket value
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {

            //fungsi spacer untuk memberi spasi anatara kolom dan gambar
            // dengan jarak 16 dp, jarak ini dipanggil dari file dimens.xml di paket value
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            //fungsi image untuk menampilkan gambar yang diambil dari resource deawable dengan nama kue_putu
            Image(
                painter = painterResource(R.drawable.kue_putu),
                contentDescription = null,
                modifier = Modifier.width(300.dp)
            )

            //fungsi spacer untuk memberi spasi anatara gambar dan teks Pesan Kue
            // dengan jarak 16 dp, jarak ini dipanggil dari file dimens.xml di paket value
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            //fungsi teks untuk menampilkan teks 'Pesan Kue'
            Text(
                text = stringResource(R.string.order_cupcakes),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        }


        // Fungsi Row untuk menampilkan baris yang didalamnya ada fungsi kolom lagi
        Row(modifier = Modifier.weight(1f, false)) {

            // fungsi kolom yang didalamnya ada teksButton dengan jarak 16 dp
            // jarak ini dipanggil dari file dimens.xml di paket value
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.padding_medium)
                )
            ) {

                /*TODO 5: parameter quantityOptions di composable StartOrderScreen,
                   Int pertama adalah ID resource untuk string yang ditampilkan pada setiap tombol.
                   Int kedua adalah jumlah kue sebenarnya.*/
                quantityOptions.forEach { item ->
                    SelectQuantityButton(
                        labelResourceId = item.first,
                        onClick = { onNextButtonClicked(item.second) }// panggil onNextButtonClicked dengan meneruskan item.second, yaitu jumlah kue
                    )
                }
            }
        }
    }
}
/*TODO : */
/**
 * Customizable button composable that displays the [labelResourceId]
 * and triggers [onClick] lambda when this composable is clicked
 */

/*TODO : */
@Composable
fun SelectQuantityButton(
    @StringRes labelResourceId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        Text(stringResource(labelResourceId))
    }
}

@Preview
@Composable
fun StartOrderPreview(){
    StartOrderScreen(
        quantityOptions = DataSource.quantityOptions,
        onNextButtonClicked = {},
        modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium))
    )
}
