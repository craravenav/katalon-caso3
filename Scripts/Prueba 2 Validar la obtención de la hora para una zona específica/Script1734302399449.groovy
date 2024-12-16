import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper

// Peticion usando el metodo GET para obtener la hora de la zona de tiempo America
def response = WS.sendRequest(findTestObject('Get Time Zone America Santiago'))

// Capturo el código de estado
int statusCode = response.getStatusCode()
// Operacion Ternaria para validar la respuesta del API
assert statusCode == 200 : "Código de estado inválido: ${statusCode}"

// Analizo el contenido JSON de la respuesta para tratarlo como objeto
def jsonResponse = new JsonSlurper().parseText(response.getResponseBodyContent())

// Valido que los campos existan con operaciones ternarias
assert jsonResponse.datetime != null : "El campo DATETIME no está presente"
assert jsonResponse.timezone != null : "El campo TIMEZONE no está presente"
assert jsonResponse.utc_offset != null : "El campo UTC_OFFSET no está presente"

// Valido que el campo 'timezone' sea igual a 'America/Santiago'
assert jsonResponse.timezone == "America/Santiago" : "El timezone es incorrecto: ${jsonResponse.timezone}"

// Log de cierre y exito
KeywordUtil.logInfo("Validaciones completadas exitosamente.")