package com.github.ralfstuckert.com.github.ralfstuckert.openpdf.markdown

import java.awt.Color

object PdfRenderContextKeys {
    val FONT_FAMILY = PdfRenderContextKey<Int>("FONT_FAMILY")
    val FONT_SIZE = PdfRenderContextKey<Float>("FONT_SIZE")
    val FONT_STYLE = PdfRenderContextKey<Int>("FONT_TYPE")
    val FONT_COLOR = PdfRenderContextKey<Color>("FONT_COLOR")

    val UNDERLINE_THICKNESS = PdfRenderContextKey<Float>("UNDERLINE_THICKNESS")
}



