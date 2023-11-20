package com.archcoders.starswarsproject

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.archcoders.starswarsproject.presentation.view.ListCharactersFragment
import com.archcoders.starswarsproject.presentation.viewmodel.ListCharacterViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.rules.RuleChain

@ExperimentalCoroutinesApi
class MainIntegrationTests {

    private val mockWebServerRule = MockServerRule()

    @get:Rule
    val testRule: RuleChain = RuleChain
        .outerRule(mockWebServerRule)
        .around(
            GrantPermissionRule.grant(
                "android.permission.ACCESS_COARSE_LOCATION"
            )
        )
        .around(ActivityScenarioRule(ListCharactersFragment::class.java))
}