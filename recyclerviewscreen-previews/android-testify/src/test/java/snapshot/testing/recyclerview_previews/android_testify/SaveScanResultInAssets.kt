package snapshot.testing.recyclerview_previews.android_testify

import org.junit.Test
import sergio.sastre.composable.preview.scanner.core.scanresult.dump.ScanResultDumper
import sergio.sastre.composable.preview.scanner.core.utils.assetsFilePath

class SaveScanResultInAssets {
    @Test
    fun `task -- save scan result in assets`() {
        val scanResultFileName = "scan_result.json"

        ScanResultDumper()
            .scanPackageTrees("snapshot.testing.recyclerview_previews.android_testify")
            .dumpScanResultToFileInAssets(fileName = scanResultFileName)

        assert(
            assetsFilePath(fileName = scanResultFileName).exists()
        )
    }
}