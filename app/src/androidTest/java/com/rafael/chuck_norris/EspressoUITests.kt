package com.rafael.chuck_norris


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.rafael.chuck_norris.util.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class EspressoUITests {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisteredIdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun searchFactTest() {
        val floatingActionButton = onView(
allOf(withId(R.id.addFact),
childAtPosition(
childAtPosition(
withId(R.id.navigation_fragment),
0),
1),
isDisplayed()))
        floatingActionButton.perform(click())
        
        val materialButton = onView(
allOf(withId(R.id.searchFact), withText("SEARCH FACT"),
childAtPosition(
childAtPosition(
withId(R.id.navigation_fragment),
0),
2),
isDisplayed()))
        materialButton.perform(click())
        
        val frameLayout = onView(
allOf(withId(R.id.retrievedFactCard),
withParent(withParent(withId(R.id.navigation_fragment))),
isDisplayed()))
        frameLayout.check(matches(isDisplayed()))
        }

    @Test
    fun filterFactSearchTest() {
        val floatingActionButton = onView(
            allOf(withId(R.id.addFact),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    1),
                isDisplayed()))
        floatingActionButton.perform(click())

        val actionMenuItemView = onView(
            allOf(withId(R.id.filter), withContentDescription("filter"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1),
                    0),
                isDisplayed()))
        actionMenuItemView.perform(click())

        val chip = onView(
            allOf(withId(R.id.chipFood), withText("Food"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.categoriesChipGroup),
                        0),
                    6),
                isDisplayed()))
        chip.perform(click())

        val view = onView(
            allOf(withId(R.id.touch_outside),
                childAtPosition(
                    allOf(withId(R.id.coordinator),
                        childAtPosition(
                            withId(R.id.container),
                            0)),
                    0),
                isDisplayed()))
        view.perform(click())

        val textView = onView(
            allOf(withId(R.id.selectedCategory), withText("Food"),
                withParent(withParent(withId(R.id.navigation_fragment))),
                isDisplayed()))
        textView.check(matches(withText("Food")))

        val materialButton = onView(
            allOf(withId(R.id.searchFact), withText("SEARCH FACT"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val frameLayout = onView(
            allOf(withId(R.id.retrievedFactCard),
                withParent(withParent(withId(R.id.navigation_fragment))),
                isDisplayed()))
        frameLayout.check(matches(isDisplayed()))
    }

    @Test
    fun addFactToDatabase() {
        val floatingActionButton = onView(
            allOf(withId(R.id.addFact),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    1),
                isDisplayed()))
        floatingActionButton.perform(click())

        val materialButton = onView(
            allOf(withId(R.id.searchFact), withText("SEARCH FACT"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(withId(R.id.addFactToDatabase), withText("Add to List"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    3),
                isDisplayed()))
        materialButton2.perform(click())

        val floatingActionButton2 = onView(
            allOf(withId(R.id.returnToList),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    4),
                isDisplayed()))
        floatingActionButton2.perform(click())

        val viewGroup = onView(
            allOf(withParent(allOf(withId(R.id.navigation_fragment),
                withParent(withId(R.id.navigation_fragment)))),
                isDisplayed()))
        viewGroup.check(matches(isDisplayed()))
    }

    @Test
    fun deleteFact() {
        val floatingActionButton = onView(
            allOf(withId(R.id.addFact),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    1),
                isDisplayed()))
        floatingActionButton.perform(click())

        val materialButton = onView(
            allOf(withId(R.id.searchFact), withText("SEARCH FACT"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(withId(R.id.addFactToDatabase), withText("Add to List"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    3),
                isDisplayed()))
        materialButton2.perform(click())

        val floatingActionButton2 = onView(
            allOf(withId(R.id.returnToList),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    4),
                isDisplayed()))
        floatingActionButton2.perform(click())

        val materialButton3 = onView(
            allOf(withId(R.id.delete_button),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recycler_view),
                        0),
                    2),
                isDisplayed()))
        materialButton3.perform(click())
    }

    @Test
    fun addFilteredFact() {
        val floatingActionButton = onView(
            allOf(withId(R.id.addFact),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    1),
                isDisplayed()))
        floatingActionButton.perform(click())

        val actionMenuItemView = onView(
            allOf(withId(R.id.filter), withContentDescription("filter"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1),
                    0),
                isDisplayed()))
        actionMenuItemView.perform(click())

        val chip = onView(
            allOf(withId(R.id.chipDev), withText("Dev"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.categoriesChipGroup),
                        0),
                    3),
                isDisplayed()))
        chip.perform(click())

        val view = onView(
            allOf(withId(R.id.touch_outside),
                childAtPosition(
                    allOf(withId(R.id.coordinator),
                        childAtPosition(
                            withId(R.id.container),
                            0)),
                    0),
                isDisplayed()))
        view.perform(click())

        val materialButton = onView(
            allOf(withId(R.id.searchFact), withText("SEARCH FACT"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    2),
                isDisplayed()))
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(withId(R.id.addFactToDatabase), withText("Add to List"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    3),
                isDisplayed()))
        materialButton2.perform(click())

        val floatingActionButton2 = onView(
            allOf(withId(R.id.returnToList),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation_fragment),
                        0),
                    4),
                isDisplayed()))
        floatingActionButton2.perform(click())

        val textView = onView(
            allOf(withId(R.id.category), withText("dev"),
                withParent(withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))),
                isDisplayed()))
        textView.check(matches(withText("dev")))
    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
