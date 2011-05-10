package br.com.caelum.stella.hibernate.validator.xml.logic;

import javax.validation.ConstraintValidatorContext;

import junit.framework.Assert;
import net.vidageek.mirror.Mirror;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.caelum.stella.hibernate.validator.xml.Max;

final public class StellaMaxValidatorTest {
	private ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);

    @Test
    public void testThatNullIsValid() {
        StellaMaxValidator validator = new StellaMaxValidator();
        Assert.assertTrue(validator.isValid(null,context));
    }

    @Test
    public void testThatAcceptsOnlyByteShortIntegerAndLong() {
        StellaMaxValidator validator = new StellaMaxValidator();

        validator.initialize(Mirror.on(AnnotatedModel.class).reflect().annotation(Max.class).atField("b"));
        Assert.assertTrue(validator.isValid((byte) 10,context));
        Assert.assertTrue(validator.isValid((short) 10,context));
        Assert.assertTrue(validator.isValid(10,context));
        Assert.assertTrue(validator.isValid((long) 10,context));
    }

    @Test
    public void testThatIsInvalidIfValueIsBiggerThanAnnotatedValue() {
        StellaMaxValidator validator = new StellaMaxValidator();
        validator.initialize(Mirror.on(AnnotatedModel.class).reflect().annotation(Max.class).atField("b"));

        Assert.assertFalse(validator.isValid(21,context));
    }

    @Test
    public void testThatIsValidIfValueIsEqualToAnnotatedValue() {
        StellaMaxValidator validator = new StellaMaxValidator();
        validator.initialize(Mirror.on(AnnotatedModel.class).reflect().annotation(Max.class).atField("b"));

        Assert.assertTrue(validator.isValid(10,context));
    }

    public static class AnnotatedModel {
        @Max(20)
        public Byte b;
    }
}
