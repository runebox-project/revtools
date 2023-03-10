package io.runebox.deobfuscator.transformer

import io.runebox.asm.tree.ClassPool
import io.runebox.asm.tree.isStatic
import io.runebox.deobfuscator.Transformer
import org.objectweb.asm.Type
import org.objectweb.asm.tree.FieldNode
import org.tinylog.kotlin.Logger
import java.lang.reflect.Modifier

class FieldSorter : Transformer {

    private var count = 0

    override fun run(pool: ClassPool) {
        pool.classes.forEach { cls ->
            count += cls.fields.size
            cls.fields = cls.fields.sortedWith(compareBy<FieldNode> { !it.isStatic() }
                .thenBy { Modifier.toString(it.access and Modifier.fieldModifiers()) }
                .thenBy { Type.getType(it.desc).className }
                .thenBy { it.name }
            )
        }

        Logger.info("Reordered $count fields.")
    }
}