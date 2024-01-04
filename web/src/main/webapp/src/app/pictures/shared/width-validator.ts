import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function widthValidator(): ValidatorFn{
  return (control: AbstractControl) : ValidationErrors | null => {
    if (control.value !== undefined && (isNaN(control.value) || control.value < 0)) {
      return { 'widthValidate': true };
    }
    return null;
  }
}
