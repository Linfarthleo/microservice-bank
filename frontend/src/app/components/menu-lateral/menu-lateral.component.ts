import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-menu-lateral',
  standalone: true,
  imports:[RouterModule],
  templateUrl: './menu-lateral.component.html',
  styleUrls: ['./menu-lateral.component.scss']
})
export class MenuLateralComponent {
  menuAbierto: boolean = true;

  toggleMenu(): void { 
    this.menuAbierto = !this.menuAbierto;
  }
}
