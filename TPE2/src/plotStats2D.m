 function ret = plotStats2D(epochs, test_errors, lrn_rates, maxIndex)
  % Plot the network stats
  clf;
  hold on;
  % plot(1:epochs, errors, '-4; Step Error;'); % No sense to plot this error if we have the same training and eval set
  plot(1:maxIndex, test_errors'(1, 1:maxIndex), '-1; Error total;')
  plot(1:maxIndex,lrn_rates'(1, 1:maxIndex), '-3; Tasa de aprendizaje;');
  plot(epochs, 0);
  title("Evolucion de la Red Neuronal", 'FontSize', 25);
  xlabel("Numero de epocas", 'FontSize', 20);
  %ylabel("Error", 'FontSize', 20);
  %mkdir("./images/");
  %print('-dpng', './images/evolution.png');
endfunction