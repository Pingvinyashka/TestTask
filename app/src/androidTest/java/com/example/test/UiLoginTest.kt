package com.example.test




import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule


import com.example.test.ui.activities.CurrencyExchangeActivity
import com.kaspersky.kaspresso.kaspresso.Kaspresso


import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class UiLoginTest : TestCase(
    Kaspresso.Builder.default().apply {

        flakySafetyParams.timeoutMs = 15_000
        flakySafetyParams.intervalMs = 3_000
    }
) {

    @get:Rule
    val activityTestRule = ActivityTestRule(CurrencyExchangeActivity::class.java, true, false)

    @Test
    fun myIconGenerateBug() {

        before {
            activityTestRule.launchActivity(null)
        }.after {
        }.run {

            step("1.Open Screen And Check Recycler is NotEmpty") {


                CurrencyExchangeScreen {

                    recycler {
                        isVisible()
                        getSize() != 0
                    }
                }
            }
            step("2.Scroll to last position and click") {

                flakySafely(
                    timeoutMs = 15_000,
                    intervalMs = 300,
                    allowedExceptions = mutableSetOf(KotlinNullPointerException::class.java)
                            as MutableSet<Class<out Throwable>>
                ) {
                    CurrencyExchangeScreen {

                        recycler {

                            scrollToEnd()

                            lastChild<CurrencyExchangeScreen.Item>() {
                                isClickable()
                                click()
                            }
                        }
                    }
                }
            }
            step("3.Click on EditText and check keyboard is ready") {

                CurrencyExchangeScreen {

                    recycler {
                        lastChild<CurrencyExchangeScreen.Item> {
                            currencyInput.isClickable()
                            currencyInput.click()

                            val inputMethodManager =
                                InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(
                                    Context.INPUT_METHOD_SERVICE
                                ) as InputMethodManager

                            check(inputMethodManager.isAcceptingText)
                        }
                    }
                }
            }

            step("4.Edit, remove, add values") {

                CurrencyExchangeScreen {

                    recycler.firstChild<CurrencyExchangeScreen.Item> {

                            currencyInput.typeText("123")
                            currencyInput.clearText()
                            currencyInput.typeText("555555")
                            currencyInput.hasAnyText()
                    }
                }
            }
        }
    }

}