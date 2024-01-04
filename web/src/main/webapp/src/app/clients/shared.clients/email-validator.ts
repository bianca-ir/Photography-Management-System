import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function emailValidator(): ValidatorFn{
  return (control: AbstractControl) : ValidationErrors | null => {
    if (control.value !== undefined && !(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(control.value))) {
      return { 'emailValidate': true };
    }
    return null;
  }
}
