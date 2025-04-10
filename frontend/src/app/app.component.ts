import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuLateralComponent } from './components/menu-lateral/menu-lateral.component';

@Component({
  selector: 'app-root',
  standalone:true,
  imports: [RouterOutlet, MenuLateralComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';
}
