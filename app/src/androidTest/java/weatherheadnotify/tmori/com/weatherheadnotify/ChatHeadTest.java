package weatherheadnotify.tmori.com.weatherheadnotify;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by mori on 11/3/16.
 */

@RunWith(AndroidJUnit4.class)
@MediumTest
public class ChatHeadTest {

    private static final String TAG = ChatHead.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        //onView(withId(R.id.chathead_layout_include)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void testContainChatHead() throws Exception {
        onView(withId(R.id.chathead_layout)).check(ViewAssertions.matches(Matchers.not(isDisplayed())));
    }

    @After
    public void unRegisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
