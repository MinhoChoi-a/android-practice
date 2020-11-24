package com.example.jetpacktrial.data

import java.util.*

class SampleDataProvider {

    //working like static values in java
    companion object {
        private val sampleText1 = "A Simple note"
        private val sampleText2 = "A note a\n line feed"
        private val sampleText3 = """ 
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum venenatis eget ante non rutrum. Ut odio leo, ullamcorper id auctor nec, rutrum id libero. Sed viverra suscipit lorem. Curabitur a dolor lacinia, efficitur turpis cursus, varius ante. Donec a elementum turpis. Suspendisse lobortis lacinia lectus. Maecenas tincidunt purus eros, in semper nibh condimentum eu. Integer quis justo mi. Nullam iaculis mi nec lectus euismod, et ultrices massa facilisis. Donec quis arcu nec orci scelerisque commodo viverra sed magna. Aliquam sodales est dolor, sed sodales magna iaculis non.

            Etiam a nulla sed enim convallis aliquam eget vitae arcu. Vestibulum scelerisque erat et lacus luctus posuere. Nunc a tristique elit. Ut viverra eget mi eget elementum. Praesent ornare malesuada enim feugiat hendrerit. Quisque efficitur metus velit, sit amet aliquam nunc gravida et. In hac habitasse platea dictumst.
        """.trimIndent() //remove any leading and trailing white space

        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getNotes() = arrayListOf(
                NoteEntity(getDate(0), sampleText1),
                NoteEntity(getDate(1), sampleText2),
                NoteEntity(getDate(2), sampleText3),
                NoteEntity(getDate(3), sampleText1),
                NoteEntity(getDate(4), sampleText2),
                NoteEntity(getDate(5), sampleText3),
                NoteEntity(getDate(6), sampleText1),
                NoteEntity(getDate(4), sampleText2),
                NoteEntity(getDate(5), sampleText3),
                NoteEntity(getDate(6), sampleText1),
                NoteEntity(getDate(4), sampleText2),
                NoteEntity(getDate(5), sampleText3),
                NoteEntity(getDate(6), sampleText1),
                NoteEntity(getDate(4), sampleText2),
                NoteEntity(getDate(5), sampleText3),
                NoteEntity(getDate(6), sampleText1)
        )
    }

}