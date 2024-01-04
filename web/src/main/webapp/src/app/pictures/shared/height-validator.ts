import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function heightValidator(): ValidatorFn{
  return (control: AbstractControl) : ValidationErrors | null => {
    if (control.value !== undefined && (isNaN(control.value) || control.value < 0)) {
      return { 'heightValidate': true };
    }
    return null;
  }
}
