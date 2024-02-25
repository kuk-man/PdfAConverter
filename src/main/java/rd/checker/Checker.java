package rd.checker;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import model.ErrorMessage;

public class Checker {
    protected ErrorMessage errors;

    protected Checker() {
        errors = new ErrorMessage();
    }

    public ErrorMessage getError() {
        return errors;
    }

    protected static boolean isNull(N nullableWrapper) {
        if (nullableWrapper.getOrElseIgnoringNPE("null").equals("null") || nullableWrapper.getIgnoringNPE().toString().equals("null"))
            return true;
        return false;
    }

    protected void checkValue(N nullableWrapper, String location) {
        if (nullableWrapper.getOrElseIgnoringNPE("null").equals("null") || nullableWrapper.getIgnoringNPE().toString().equals("null"))
            errors.setErrorMassage("Missing " + location);
    }

    protected void checkNull(N nullableWrapper, String location) {
        if (!nullableWrapper.getOrElseIgnoringNPE("null").equals("null") && !nullableWrapper.getIgnoringNPE().toString().equals("null"))
            errors.setErrorMassage("Delete " + location);
    }

    // NullableWrapper
    protected class N<T> {
        private final Supplier<T> supplier;
        private T defaultValue = null;
      
        public N(Supplier<T> supplier) {
            this.supplier = Optional.ofNullable(supplier).orElse(() -> null);
        }
      
        public N(Supplier<T> supplier, T defaultValue) {
            this.supplier = Optional.ofNullable(supplier).orElse(() -> null);
            this.defaultValue = defaultValue;
        }
      
        public T get() {
            return Optional.ofNullable(supplier.get()).orElse(defaultValue);
        }
      
        public T getOrElse(T newDefault) {
            return Optional.ofNullable(supplier.get()).orElse(newDefault);
        }

        public T getIgnoringNPE() {
            T evaluated = null;
            try {
              evaluated = supplier.get();
            } catch (NullPointerException ignored) {
            }
            return Optional.ofNullable(evaluated).orElse(defaultValue);
        }
        
        public T getOrElseIgnoringNPE(T newDefault) {
            T evaluated = null;
            try {
              evaluated = supplier.get();
            } catch (NullPointerException ignored) {
            }
            return Optional.ofNullable(evaluated).orElse(newDefault);
        }
      
        public <R>N<R> map(Function<T,R> transformer) {
            return mapWithDefault(transformer, null);
        }
      
        public <R>N<R> mapWithDefault(Function<T,R> transformer, R newDefault) {
            return Optional.ofNullable(get())
                .map(transformer)
                .map(r -> new N<>(() -> r, newDefault))
                .orElse(new N<>(() -> newDefault));
        }

        public <R>N<R> mapWithDefaultIgnoringNPE(Function<T,R> transformer, R newDefault) {
            return Optional.ofNullable(getIgnoringNPE())
                .map(transformer)
                .map(r -> new N<>(() -> r))
                .orElse(new N<>(() -> newDefault));
        }
          
        public <R>N<R> mapIgnoringNPE(Function<T,R> transformer) {
            return mapWithDefaultIgnoringNPE(transformer, null);
        }
    }
}
