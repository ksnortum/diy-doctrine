package net.snortum.doctrine.mvc;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * Implemented only so that testing can use this object. Needed
 * {@link #setErrors(boolean)}.
 * 
 * @author Knute
 * 
 */
public class BindingResultForTest implements BindingResult {

	private boolean errors;

	public void setErrors( boolean errors ) {
		this.errors = errors;
	}

	@Override
	public String getObjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNestedPath( String nestedPath ) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNestedPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pushNestedPath( String subPath ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void popNestedPath() throws IllegalStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void reject( String errorCode ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reject( String errorCode, String defaultMessage ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reject( String errorCode, Object[] errorArgs,
			String defaultMessage ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectValue( String field, String errorCode ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectValue( String field, String errorCode,
			String defaultMessage ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectValue( String field, String errorCode,
			Object[] errorArgs, String defaultMessage ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAllErrors( Errors errors ) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasErrors() {
		return errors;
	}

	@Override
	public int getErrorCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ObjectError> getAllErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasGlobalErrors() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getGlobalErrorCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ObjectError> getGlobalErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectError getGlobalError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFieldErrors() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getFieldErrorCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FieldError> getFieldErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FieldError getFieldError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFieldErrors( String field ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getFieldErrorCount( String field ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FieldError> getFieldErrors( String field ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FieldError getFieldError( String field ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getFieldValue( String field ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getFieldType( String field ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getRawFieldValue( String field ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyEditor findEditor( String field, Class<?> valueType ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyEditorRegistry getPropertyEditorRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addError( ObjectError error ) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] resolveMessageCodes( String errorCode ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] resolveMessageCodes( String errorCode, String field ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recordSuppressedField( String field ) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getSuppressedFields() {
		// TODO Auto-generated method stub
		return null;
	}

}
