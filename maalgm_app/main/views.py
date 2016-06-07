from django.shortcuts import render

# Create your views here.


def main_page(request):
    dark_theme = True
    return render(request, 'main/main_page.html', {'dark_theme': dark_theme})
