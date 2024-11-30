import os

def generate_latex():
    latex_content = r"""
\documentclass[12pt]{article}
\usepackage{graphicx}
\usepackage{float}
\usepackage[margin=1in]{geometry}
\usepackage{caption}
\usepackage{subcaption}

\begin{document}

\section{Decision Tree Analysis Results}

% Combined Accuracy Distribution
\subsection{Overall Accuracy Distribution}
\begin{figure}[H]
    \centering
    \includegraphics[width=0.9\textwidth]{plots/combined_accuracy_distribution.png}
    \caption{Comparison of accuracy distributions across different strategy and metric combinations.}
    \label{fig:combined-accuracy}
\end{figure}
\newpage

% Confusion Matrices
\subsection{Confusion Matrices}
\begin{figure}[H]
    \centering
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/confusion_matrix_BEST_GINI_IMPURITY.png}
        \caption{BEST - GINI IMPURITY}
    \end{subfigure}
    \hfill
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/confusion_matrix_BEST_INFORMATION_GAIN.png}
        \caption{BEST - INFORMATION GAIN}
    \end{subfigure}
    
    \vspace{0.5cm}
    
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/confusion_matrix_TOP_THREE_GINI_IMPURITY.png}
        \caption{TOP THREE - GINI IMPURITY}
    \end{subfigure}
    \hfill
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/confusion_matrix_TOP_THREE_INFORMATION_GAIN.png}
        \caption{TOP THREE - INFORMATION GAIN}
    \end{subfigure}
    \caption{Confusion matrices for different strategy-metric combinations showing classification performance across classes.}
    \label{fig:confusion-matrices}
\end{figure}
\newpage

% Error Rates
\subsection{Error Rates Analysis}
\begin{figure}[H]
    \centering
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/error_rates_BEST_GINI_IMPURITY.png}
        \caption{BEST - GINI IMPURITY}
    \end{subfigure}
    \hfill
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/error_rates_BEST_INFORMATION_GAIN.png}
        \caption{BEST - INFORMATION GAIN}
    \end{subfigure}
    
    \vspace{0.5cm}
    
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/error_rates_TOP_THREE_GINI_IMPURITY.png}
        \caption{TOP THREE - GINI IMPURITY}
    \end{subfigure}
    \hfill
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/error_rates_TOP_THREE_INFORMATION_GAIN.png}
        \caption{TOP THREE - INFORMATION GAIN}
    \end{subfigure}
    \caption{Error rates per class for different strategy-metric combinations.}
    \label{fig:error-rates}
\end{figure}
\newpage

% Attribute Depths
\subsection{Attribute Depth Analysis}
\begin{figure}[H]
    \centering
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/attribute_depths_BEST_GINI_IMPURITY.png}
        \caption{BEST - GINI IMPURITY}
    \end{subfigure}
    \hfill
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/attribute_depths_BEST_INFORMATION_GAIN.png}
        \caption{BEST - INFORMATION GAIN}
    \end{subfigure}
    
    \vspace{0.5cm}
    
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/attribute_depths_TOP_THREE_GINI_IMPURITY.png}
        \caption{TOP THREE - GINI IMPURITY}
    \end{subfigure}
    \hfill
    \begin{subfigure}[b]{0.45\textwidth}
        \includegraphics[width=\textwidth]{plots/attribute_depths_TOP_THREE_INFORMATION_GAIN.png}
        \caption{TOP THREE - INFORMATION GAIN}
    \end{subfigure}
    \caption{Distribution of attribute depths in the decision tree for different strategy-metric combinations.}
    \label{fig:attribute-depths}
\end{figure}
\newpage

% Training Metrics (Individual Pages)
\subsection{Training Metrics Analysis}

\begin{figure}[H]
    \centering
    \includegraphics[width=0.9\textwidth]{plots/training_metrics_BEST_GINI_IMPURITY.png}
    \caption{Training metrics for BEST strategy with GINI IMPURITY metric showing relationships between training percentage and various performance measures.}
    \label{fig:training-best-gini}
\end{figure}
\newpage

\begin{figure}[H]
    \centering
    \includegraphics[width=0.9\textwidth]{plots/training_metrics_BEST_INFORMATION_GAIN.png}
    \caption{Training metrics for BEST strategy with INFORMATION GAIN metric showing relationships between training percentage and various performance measures.}
    \label{fig:training-best-ig}
\end{figure}
\newpage

\begin{figure}[H]
    \centering
    \includegraphics[width=0.9\textwidth]{plots/training_metrics_TOP_THREE_GINI_IMPURITY.png}
    \caption{Training metrics for TOP THREE strategy with GINI IMPURITY metric showing relationships between training percentage and various performance measures.}
    \label{fig:training-top3-gini}
\end{figure}
\newpage

\begin{figure}[H]
    \centering
    \includegraphics[width=0.9\textwidth]{plots/training_metrics_TOP_THREE_INFORMATION_GAIN.png}
    \caption{Training metrics for TOP THREE strategy with INFORMATION GAIN metric showing relationships between training percentage and various performance measures.}
    \label{fig:training-top3-ig}
\end{figure}

\end{document}
"""

    # Write to file
    with open('decision_tree_report.tex', 'w') as f:
        f.write(latex_content)

if __name__ == "__main__":
    generate_latex()
    print("LaTeX file 'decision_tree_report.tex' has been generated!")