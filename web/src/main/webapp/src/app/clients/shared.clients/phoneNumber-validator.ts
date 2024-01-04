import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function phoneNumberValidator(): ValidatorFn{
  return (control: AbstractControl) : ValidationErrors | null => {
    if (control.value !== undefined && !(/^[0-9]{10}$/.test(control.value))) {
      return { 'phoneNumberValidate': true };
    }
    return null;
  }
}
