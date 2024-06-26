package com.github.ralfstuckert.openpdf.markdown

import com.github.ralfstuckert.openpdf.markdown.document.document
import com.github.ralfstuckert.openpdf.markdown.renderer.TableMarkdownRenderer.Companion.TABLE_RENDER_CONTEXT_KEY
import org.junit.jupiter.api.Test
import java.awt.Color
import java.io.File

class TableMarkdownRendererTest {

    @Test
    fun header() {
        val doc = document {
            markdown {
                +"A simple table"
                +"""
                    | **Column 1** | **Column 2** | **Column 3** |
                    |----------|----------|----------|
                    | Hello there, what's going on | Beat's me | The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog |
                    | | hihi | |
                                 
                                                            
                """.trimIndent()
            }

            markdown {
                markdownRendererRegistry = MarkdownRendererRegistry(defaultRenderContext).apply {
                    registerRenderContextFunction(TABLE_RENDER_CONTEXT_KEY, true) {
                        derive {
                            this[PdfRenderContextKeys.WIDTH_PERCENTAGE] = 80f
                            this[PdfRenderContextKeys.WEIGHTED_WIDTHS_ENABLED] = true
                        }
                    }
                }
                +"A table with 80% width and weighted columns"
                +"""                   
                    | **Small Column** | **Big Column** | **Medium Column** |
                    |---|----------|-----|
                    | Hello there, what's going on | Beat's me | The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog |
                    | | hihi | |
                    
                    
               """.trimIndent()
            }

            markdown {
                markdownRendererRegistry = MarkdownRendererRegistry(defaultRenderContext).apply {
                    registerRenderContextFunction(TABLE_RENDER_CONTEXT_KEY, true) {
                        derive {
                            this[PdfRenderContextKeys.BORDER_COLOR] = Color.blue
                            this[PdfRenderContextKeys.BORDER_WIDTH] = 2f
                        }
                    }
                }
                +" A table with a custom border"
                +"""    
                    | **Column 1** | **Column 2** | **Column 3** |
                    |----------|----------|----------|
                    | Hello there, what's going on | Beat's me | The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog |
                    | | hihi | |
                    
                    
               """.trimIndent()
            }

            markdown {
                markdownRendererRegistry = MarkdownRendererRegistry(defaultRenderContext).apply {
                    registerRenderContextFunction(TABLE_RENDER_CONTEXT_KEY, true) {
                        derive {
                            this[PdfRenderContextKeys.BORDER_WIDTH] = 0f
                        }
                    }
                }
                +"A table without border"
                +"""
                    | **Column 1** | **Column 2** | **Column 3** |
                    |----------|----------|----------|
                    | Hello there, what's going on | Beat's me | The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog |
                    | | hihi | |
                    
                    
               """.trimIndent()
            }

            markdown {
                markdownRendererRegistry = MarkdownRendererRegistry(defaultRenderContext).apply {
                    registerRenderContextFunction(TABLE_RENDER_CONTEXT_KEY, true) {
                        derive {
                            this[PdfRenderContextKeys.COLSPAN_ENABLED] = true
                        }
                    }
                }
                +"A table using colspan"
                +"""
                    | **Column 1** | **Column 2** | **Column 3** | **Column 4** | **Column 5** |
                    |----------|----------|----------|----------|----------|
                    | one | two | three | four | five |
                    | Hello there, what's going on | The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog ||| yeehaw |             
                            
                                        
                """.trimIndent()
            }

        }
 //       File("table.pdf").writeBytes(doc)
        doc shouldEqual "table.pdf"


    }
}

