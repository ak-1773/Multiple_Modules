package {{packageName}}.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.therouter.router.Route
import com.example.lib_base.BaseActivity
import com.example.lib_base.ui.theme.ArchivesTheme
import {{packageName}}.ui.{{formatName}}UI
import {{packageName}}.vm.{{viewModelName}}

@Route(path = "/{{name}}/{{activityName}}")
class {{activityName}} : BaseActivity() {
    private val viewModel: {{viewModelName}} by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArchivesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.White)
                    ) {
                        {{formatName}}UI()
                    }
                }
            }
        }
    }
}