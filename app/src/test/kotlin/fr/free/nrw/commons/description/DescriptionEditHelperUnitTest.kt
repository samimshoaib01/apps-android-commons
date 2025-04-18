package fr.free.nrw.commons.description

import android.content.Context
import fr.free.nrw.commons.Media
import fr.free.nrw.commons.actions.PageEditClient
import fr.free.nrw.commons.notification.NotificationHelper
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import fr.free.nrw.commons.R
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.reflect.Method

class DescriptionEditHelperUnitTest {
    private lateinit var helper: DescriptionEditHelper

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var notificationHelper: NotificationHelper

    @Mock
    private lateinit var pageEditClient: PageEditClient

    @Mock
    private lateinit var media: Media

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        helper = DescriptionEditHelper(notificationHelper, pageEditClient)
    }

    @Test
    fun testAddDescription() {
        `when`(media.filename).thenReturn("")
        `when`(
            pageEditClient.edit(
                anyString(),
                anyString(),
                anyString(),
            ),
        ).thenReturn(Observable.just(true))
        helper.addDescription(context, media, "test")
        verify(pageEditClient, times(1)).edit(anyString(), anyString(), anyString())
    }

    @Test
    fun testAddCaption() {
        `when`(media.filename).thenReturn("")
        `when`(
            pageEditClient.setCaptions(
                anyString(),
                anyString(),
                anyString(),
                anyString(),
            ),
        ).thenReturn(Observable.just(0))
        helper.addCaption(context, media, "test", "test")
        verify(pageEditClient, times(1)).setCaptions(
            anyString(),
            anyString(),
            anyString(),
            anyString(),
        )
    }

    @Test
    fun testShowCaptionEditNotificationCaseFalse() {
        `when`(context.getString(R.string.caption_edit_helper_show_edit_title))
            .thenReturn("Edit Caption")
        `when`(context.getString(R.string.coordinates_edit_helper_show_edit_title_success))
            .thenReturn("Success")
        `when`(context.getString(R.string.caption_edit_helper_show_edit_message))
            .thenReturn("Edit caption was successful")
        `when`(context.getString(R.string.caption_edit_helper_edit_message_else))
            .thenReturn("Edit caption failed")

        val method: Method =
            DescriptionEditHelper::class.java.getDeclaredMethod(
                "showCaptionEditNotification",
                Context::class.java,
                Media::class.java,
                Int::class.java,
            )
        method.isAccessible = true
        assertEquals(method.invoke(helper, context, media, 0), false)
    }

    @Test
    fun testShowCaptionEditNotificationCaseTrue() {
        `when`(context.getString(R.string.caption_edit_helper_show_edit_title))
            .thenReturn("Edit Caption")
        `when`(context.getString(R.string.coordinates_edit_helper_show_edit_title_success))
            .thenReturn("Success")
        `when`(context.getString(R.string.caption_edit_helper_show_edit_message))
            .thenReturn("Edit caption was successful")
        `when`(context.getString(R.string.caption_edit_helper_edit_message_else))
            .thenReturn("Edit caption failed")

        val method: Method =
            DescriptionEditHelper::class.java.getDeclaredMethod(
                "showCaptionEditNotification",
                Context::class.java,
                Media::class.java,
                Int::class.java,
            )
        method.isAccessible = true
        assertEquals(method.invoke(helper, context, media, 1), true)
    }

    @Test
    fun testShowDescriptionEditNotificationCaseFalse() {
        `when`(context.getString(R.string.description_edit_helper_show_edit_title))
            .thenReturn("Edit Description")
        `when`(context.getString(R.string.coordinates_edit_helper_show_edit_title_success))
            .thenReturn("Success")
        `when`(context.getString(R.string.description_edit_helper_show_edit_message))
            .thenReturn("Edit message")
        `when`(context.getString(R.string.description_edit_helper_edit_message_else))
            .thenReturn("Edit failed")

        val method: Method =
            DescriptionEditHelper::class.java.getDeclaredMethod(
                "showDescriptionEditNotification",
                Context::class.java,
                Media::class.java,
                Boolean::class.java,
            )
        method.isAccessible = true
        assertEquals(method.invoke(helper, context, media, false), false)
    }

    @Test
    fun testShowDescriptionEditNotificationCaseTrue() {
        `when`(context.getString(R.string.description_edit_helper_show_edit_title))
            .thenReturn("Edit Description")
        `when`(context.getString(R.string.coordinates_edit_helper_show_edit_title_success))
            .thenReturn("Success")
        `when`(context.getString(R.string.description_edit_helper_show_edit_message))
            .thenReturn("Edit message")
        `when`(context.getString(R.string.description_edit_helper_edit_message_else))
            .thenReturn("Edit failed")

        val method: Method =
            DescriptionEditHelper::class.java.getDeclaredMethod(
                "showDescriptionEditNotification",
                Context::class.java,
                Media::class.java,
                Boolean::class.java,
            )
        method.isAccessible = true
        assertEquals(method.invoke(helper, context, media, true), true)
    }
}
