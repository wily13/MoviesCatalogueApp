package com.example.moviescatalogueapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.moviescatalogueapp.R
import com.example.moviescatalogueapp.utils.EspressoIdlingResource
import com.example.moviescatalogueapp.utils.FormatedMethod
import com.example.moviescatalogueapp.utils.MoviesDataDummy
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    private val dummyMovies = MoviesDataDummy.generateDummyMovies()
    private val dummyTvShows = MoviesDataDummy.generateDummyTvShows()
    private val dummyCredit = MoviesDataDummy.generateDummyCredits()
    private val dummyCountry = MoviesDataDummy.generateDummyCountry()
    private val dummyLanguage = MoviesDataDummy.generateDummyLanguage()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)

        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_firstair_date)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_firstairdate_title)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_lastair_date)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_lastairdate_title)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_creator)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_creator_title)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_type)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_type_title)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_country)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_country_title)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        dummyMovies[0].apply {
            onView(withId(R.id.tv_title)).check(matches(withText("${title} (${FormatedMethod.getYearRelease(releaseDate)})")))
            onView(withId(R.id.tv_tagline)).check(matches(withText(tagline)))
            onView(withId(R.id.tv_daterelease)).check(matches(withText(FormatedMethod.getDateFormat(releaseDate))))
            onView(withId(R.id.tv_director)).check(matches(withText(dummyCredit[0].name)))
            onView(withId(R.id.tv_description)).check(matches(withText(overview)))
            onView(withId(R.id.tv_status)).check(matches(withText(status)))
            onView(withId(R.id.tv_language)).check(matches(withText(dummyLanguage[0].englishName)))
            onView(withId(R.id.tv_budget)).check(matches(withText(FormatedMethod.roundOffInt(budget))))
            onView(withId(R.id.tv_revenue)).check(matches(withText(FormatedMethod.roundOffInt(revenue))))
        }

    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_daterelease)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_daterelease_title)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_director)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_director_title)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_budget)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_budget_title)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_revenue)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tv_revenue_title)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        dummyTvShows[0].apply {
            onView(withId(R.id.tv_title)).check(matches(withText("${title} (${FormatedMethod.getYearRelease(lastAirDate)})")))
            onView(withId(R.id.tv_tagline)).check(matches(withText(tagline)))
            onView(withId(R.id.tv_firstair_date)).check(matches(withText(FormatedMethod.getDateFormat(firstAirDate))))
            onView(withId(R.id.tv_lastair_date)).check(matches(withText(FormatedMethod.getDateFormat(lastAirDate))))
            onView(withId(R.id.tv_creator)).check(matches(withText(createdBy[0].name)))
            onView(withId(R.id.tv_description)).check(matches(withText(overview)))
            onView(withId(R.id.tv_status)).check(matches(withText(status)))
            onView(withId(R.id.tv_country)).check(matches(withText(dummyCountry[0].name)))
            onView(withId(R.id.tv_type)).check(matches(withText(type)))
            onView(withId(R.id.tv_language)).check(matches(withText(dummyLanguage[0].englishName)))
        }

    }

    @Test
    fun buttonShareMovies() {
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab)).perform(ViewActions.click())
    }

    @Test
    fun buttonShareTvShows() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab)).perform(ViewActions.click())
    }
}