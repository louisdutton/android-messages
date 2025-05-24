// ktlint-disable filename
package digital.dutton.example.coordinators.languageCoordinator

import digital.dutton.example.models.languageContent.Languages
import digital.dutton.example.models.languageContent.UIContent
import digital.dutton.example.utils.json.ReadJSONFromAssets
import com.google.gson.Gson

fun LanguageCoordinator.getContent(language: Languages): UIContent? {
    // Check that hte context exists
    val context = this.context ?: return null
    // Gather the language data
    val fileName = when (language) {
        Languages.SPANISH -> "es"
        else -> "en"
    }
    val jsonString = ReadJSONFromAssets(context, "strings/$fileName.json")
    // Make sure that there is data
    if (jsonString.isEmpty()) return null
    // Return Data as UIContent object
    return Gson().fromJson(jsonString, UIContent::class.java)
}
